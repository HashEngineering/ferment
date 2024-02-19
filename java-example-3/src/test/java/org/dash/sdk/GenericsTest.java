package org.dash.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenericsTest extends BaseTest {
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
    public void createVecU8InRustAndDestroy() {
        VecU8Holder holder = example.vecU8HolderCtor(identifier);
        example.vecU8HolderDestroy(holder);
        // does this leak Vec_u8
    }

    @Test
    public void createVecU8HolderInJavaAndDestroy() {
        VecU8Holder holder = new VecU8Holder(identifier);
        holder.delete();
    }
}
