package org.dash.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class generics {
    static {
        System.loadLibrary("sdklib");
    }

    @Test
    public void innerOuter() {
        OuterStruct os = new OuterStruct(1, 2, 3, 4);
        assertEquals(1L, os.getFirst().getFirst().longValue());
        assertEquals(3L, os.getSecond().getFirst().longValue());
        InnerStruct is = os.getFirst();
        assertEquals(1L, is.getFirst().longValue());
        os.delete();
        // assertEquals(1L, is.getFirst().longValue()); // this will fail, meaning the Innerstruct is destroyed
    }

    @Test
    public void innerOuter2() {
        InnerStruct is1 = new InnerStruct(1, 2);
        InnerStruct is2 = new InnerStruct(3, 4);
        OuterStruct os = new OuterStruct(is1, is2);
        assertEquals(1L, os.getFirst().getFirst().longValue());
        assertEquals(3L, os.getSecond().getFirst().longValue());
        InnerStruct is = os.getFirst();
        assertEquals(1L, is.getFirst().longValue());
        os.delete();
        assertEquals(1L, is1.getFirst().longValue());
        assertNotEquals(1L, is.getFirst().longValue());
        is1.delete();
        is2.delete();
    }

    @Test
    public void ctor() {
        Identifier identifier = example.identifierCtor(example.identifierBytes32Ctor(new byte[32]));
        // identifier doesn't own the pointer
        assertFalse(identifier.swigCMemOwn);
        identifier.delete();

        Identifier identifier1 = new Identifier(new byte[32]);
        assertTrue(identifier1.swigCMemOwn);
        identifier1.delete();
    }

    @Test
    public void createVecU8InRustAndDestroy() {
        VecU8Holder holder = example.vecU8HolderCtor(new byte[32]);
        example.vecU8HolderDestroy(holder);
        // does this leak Vec_u8
    }

    @Test
    public void createVecU8HolderInJavaAndDestroy() {
        VecU8Holder holder = new VecU8Holder(new byte[32]);
        holder.delete();
    }

    @Test
    public void binaryDataTest() {
        byte[] bytes = new byte[32];
        for (byte i = 0; i < 32; ++i)
            bytes[i] = i;
        BinaryData data = new BinaryData(bytes);
        System.out.printf("BinaryData: %x%n", BinaryData.getCPtr(data));
        assertEquals(32, data.get_0().length);
        for (byte a : bytes)
            System.out.printf("%d ", a);
        System.out.println();
        for (byte a : data.get_0())
            System.out.printf("%d ", a);
        System.out.println();
        assertArrayEquals(bytes, data.get_0());
        data.delete(); // does not crash
    }

    // if the object is created in Java, then the Java object does own it
    // delete will call the destructor
    @Test
    public void createBinaryDataInJavaAndDestroyTest() {
        System.out.println("Java-------");
        byte[] bytes = new byte[32];
        for (byte i = 0; i < 32; ++i)
            bytes[i] = i;
        BinaryData data = new BinaryData(bytes);
        System.out.printf("BinaryData %x\n", BinaryData.getCPtr(data));
        data.delete(); // does not crash
    }

    // if the object is created in rust, then the Java object does not own it
    // .delete will not call the destructor
    @Test
    public void createBinaryDataInRustAndDestory() {
        System.out.println("Rust-------");
        byte[] bytes = new byte[32];
        BinaryData data = example.binaryDataCtor(bytes);
        System.out.printf("BinaryData %s %x\n", data.swigCMemOwn ? "owns" : "doesn't own", BinaryData.getCPtr(data));
        example.binaryDataDestroy(data);
    }
}
