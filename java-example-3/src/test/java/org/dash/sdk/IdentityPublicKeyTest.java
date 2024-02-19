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

public class IdentityPublicKeyTest extends BaseTest {


    @Test
    public void enumConstructorDestructorTest() {
        // can't use this in java
    }

    @Test
    public void createKeyIDinRustAndDestroy() {
        KeyID id = example.keyIDCtor(1);
        assertEquals(1, id.toInt());
        example.keyIDDestroy(id);
    }

    @Test
    public void createKeyIDinJavaAndDestroy() {
        KeyID id = new KeyID(1);
        assertEquals(1, id.toInt());
        id.delete();
    }

    @Test
    public void createTimestampMillisinRustAndDestroy() {
        long timestamp = System.currentTimeMillis();
        TimestampMillis ts = example.timestampMillisCtor(BigInteger.valueOf(timestamp));
        assertEquals(timestamp, ts.toLong());
        example.timestampMillisDestroy(ts);
    }

    @Test
    public void createTimestampMillisinJavaAndDestroy() {
        long timestamp = System.currentTimeMillis();
        TimestampMillis ts = new TimestampMillis(timestamp);
        assertEquals(timestamp, ts.toLong());
        ts.delete();
    }

    @Test
    public void createTimestampMillisinJavaAndDestroy2() {
        long timestamp = System.currentTimeMillis();
        TimestampMillis ts = new TimestampMillis(); // uses time(NULL) to get the current time
        assertEquals(timestamp/1000, ts.toLong()/1000);
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
    public void createIdentityPublicKeyInJavaAndDestroyWithNullsTest() {
        Identifier contract = new Identifier(contractIdentifier);
        KeyID keyId = new KeyID(2);

        Purpose purpose = Purpose.Purpose_AUTHENTICATION;
        KeyType keyType = KeyType.KeyType_ECDSA_SECP256K1;
        SecurityLevel securityLevel = SecurityLevel.SecurityLevel_HIGH;

        BinaryData data = new BinaryData(identifier);
        assertArrayEquals(identifier, data.get_0());

        IdentityPublicKeyV0 ipkv0 = new IdentityPublicKeyV0(
                keyId,
                purpose,
                securityLevel,
                null,
                keyType,
                false,
                data,
                null
        );
        System.out.printf("identitypublickeyv0 %x\n", IdentityPublicKeyV0.getCPtr(ipkv0));
        System.out.flush();
        assertEquals(2, ipkv0.getId().toInt());
        assertEquals(Purpose.Purpose_AUTHENTICATION, ipkv0.getPurpose());
        assertEquals(KeyType.KeyType_ECDSA_SECP256K1, ipkv0.getKeyType());
        assertEquals(SecurityLevel.SecurityLevel_HIGH, ipkv0.getSecurityLevel());
        assertArrayEquals(identifier, ipkv0.getData().get_0());
        assertFalse(ipkv0.getRead_only());
        assertNull(ipkv0.getDisabled_at());
        assertNull(ipkv0.getContract_bounds());

        ipkv0.delete(); // this still crashes, why? it was due to contract bounds
    }


    @Test
    public void createIdentityPublicKeyInJavaAndDestroyTest() {
        KeyID keyId = new KeyID(0);
        Purpose purpose = Purpose.Purpose_AUTHENTICATION;
        KeyType keyType = KeyType.KeyType_ECDSA_SECP256K1;
        SecurityLevel securityLevel = SecurityLevel.SecurityLevel_HIGH;

        BinaryData data = new BinaryData(publicKey);
        ContractBounds contractBounds = example.contractBoundsSingleContractCtor(new Identifier(contractIdentifier));
        TimestampMillis timestampMillis = new TimestampMillis();
        assertArrayEquals(publicKey, data.get_0());

        // this constructor will clone all arguments
        IdentityPublicKeyV0 ipkv0 = new IdentityPublicKeyV0(keyId, purpose, securityLevel,
                contractBounds,
                keyType,
                false, data, timestampMillis);
        System.out.printf("ipkv0 0x%016x\n", IdentityPublicKeyV0.getCPtr(ipkv0));
        System.out.flush();
        ipkv0.delete(); // this still crashes, why?
        data.delete();
        contractBounds.delete();
        timestampMillis.delete();
        ipkv0 = null;
    }

}
