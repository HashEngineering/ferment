package org.dash.sdk;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IdentityPublicKeyTest {
    static {
        System.loadLibrary("sdklib");
    }
    static MemoryFactory memoryFactory = MemoryFactory.getInstance();

    @BeforeAll
    public static void start() {

    }

    @AfterEach
    public void end() {
        System.out.printf("objects: %d\n", memoryFactory.size());
    }

    @Test
    public void enumConstructorDestructorTest() {
        // can't use this in java
    }

    @Test
    public void keyIDConstDestTest() {
        KeyID id = example.keyIDCtor(0);
        example.keyIDDestroy(id);
    }

    @Test
    public void createKeyIDinJavaAndDestroy() {
        KeyID id = new KeyID(0);
        id.delete();
    }

    @Test
    public void createTimestampMillisinRustAndDestroy() {
        TimestampMillis ts = example.timestampMillisCtor(BigInteger.valueOf(System.currentTimeMillis()));
        example.timestampMillisDestroy(ts);
    }

    @Test
    public void createTimestampMillisinJavaAndDestroy() {
        TimestampMillis ts = new TimestampMillis(0);
        ts.delete();
    }

    @Test
    public void enumTest() {
        Purpose purpose = Purpose.Purpose_AUTHENTICATION;
        KeyType keyType = KeyType.KeyType_ECDSA_SECP256K1;
        SecurityLevel securityLevel = SecurityLevel.SecurityLevel_HIGH;
        assertEquals(KeyType.KeyType_ECDSA_SECP256K1, keyType);
        assertEquals(Purpose.Purpose_AUTHENTICATION, purpose);
        assertEquals(SecurityLevel.SecurityLevel_HIGH, securityLevel);

        //Purpose purpose1 = example.purposeAUTHENTICATIONCtor();

        example.purposeDestroy(example.purposeAUTHENTICATIONCtor());
    }

    @Test
    public void identityPublicKeyTest() {
        byte[] myIdentityBytes = new byte[32];
        byte[] contractBytes = new byte[32];

        for (byte i = 0; i < 32; ++i) {
            myIdentityBytes[i] = i;
            contractBytes[i] = 1;
        }
        Identifier contract = new Identifier(contractBytes);
        KeyID keyId = new KeyID(0);
        //long keyId = 0;//BigInteger.ZERO;
        Purpose purpose = Purpose.Purpose_AUTHENTICATION;
        KeyType keyType = KeyType.KeyType_ECDSA_SECP256K1;
        SecurityLevel securityLevel = SecurityLevel.SecurityLevel_HIGH;
        ContractBounds contractBounds = example.contractBoundsSingleContractCtor(contract);
        byte[] bytes = new byte[32];
        for (byte i = 0; i < 32; ++i)
            bytes[i] = i;
        BinaryData data = new BinaryData(bytes);
        assertArrayEquals(bytes, data.get_0());

        IdentityPublicKeyV0 ipkv0 = new IdentityPublicKeyV0(keyId, purpose, securityLevel,
                contractBounds,
                keyType,
                false, data, null);
        System.out.printf("identitypublickeyv0 %x\n", IdentityPublicKeyV0.getCPtr(ipkv0));
        System.out.flush();
        assertEquals(0, ipkv0.getId().get_0());
        assertEquals(Purpose.Purpose_AUTHENTICATION, ipkv0.getPurpose());
        assertEquals(KeyType.KeyType_ECDSA_SECP256K1, ipkv0.getKeyType());
        assertEquals(SecurityLevel.SecurityLevel_HIGH, ipkv0.getSecurityLevel());
        assertArrayEquals(bytes, ipkv0.getData().get_0());
        assertFalse(ipkv0.getRead_only());
        assertEquals(null, ipkv0.getDisabled_at());

        ipkv0.delete(); // this still crashes, why? it was due to contract bounds
        ipkv0 = null;
    }


    @Test
    public void createIdentityPublicKeyInJavaAndDestroyTest() {
        KeyID keyId = new KeyID(0);
        Purpose purpose = Purpose.Purpose_AUTHENTICATION;
        KeyType keyType = KeyType.KeyType_ECDSA_SECP256K1;
        SecurityLevel securityLevel = SecurityLevel.SecurityLevel_HIGH;
        byte[] bytes = new byte[32];
        for (byte i = 0; i < 32; ++i)
            bytes[i] = i;
        BinaryData data = new BinaryData(bytes);
        ContractBounds contractBounds = example.contractBoundsSingleContractCtor(new Identifier(bytes));
        assertArrayEquals(bytes, data.get_0());

        IdentityPublicKeyV0 ipkv0 = new IdentityPublicKeyV0(keyId, purpose, securityLevel,
                contractBounds,
                keyType,
                false, data, null);
        System.out.printf("ipkv0 0x%016x\n", IdentityPublicKeyV0.getCPtr(ipkv0));
        System.out.flush();
        ipkv0.delete(); // this still crashes, why?
        ipkv0 = null;
    }

}
