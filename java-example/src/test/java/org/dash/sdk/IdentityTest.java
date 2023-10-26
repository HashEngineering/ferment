package org.dash.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IdentityTest {
    static {
        System.loadLibrary("sdklib");
    }

    @Test
    public void getAnIdentityTest() {
        Identity identity = example.ffiGetAnIdentity();
        assertEquals(Identity_FFI_Tag.Identity_FFI_V0, identity.getTag());
        assertEquals(2, identity.getV0().getBalance().longValue());
        assertEquals(1, identity.getV0().getRevision().get_0().longValue());
    }

    @Test
    public void getIdentityTest() {
        byte[] id = new byte[32];
        id[0] = 1;
        id[1] = 2;
        short [] idShort = new short[32];
        idShort[0] = 1;
        idShort[1] = 2;
        IdentifierBytes32 identifierBytes32 = new IdentifierBytes32(idShort);
        //identifierBytes32.set_0(id);
        Identifier identifier = new Identifier();
        identifier.set_0(identifierBytes32);
        Identity identity = example.ffiGetIdentity(identifier);
        assertEquals(Identity_FFI_Tag.Identity_FFI_V0, identity.getTag());
        assertEquals(2, identity.getV0().getBalance().longValue());
        assertEquals(1, identity.getV0().getRevision().get_0().longValue());
        assertNotNull(identity.getV0().getId().get_0().get_0());
        assertArrayEquals(id, identity.getV0().getId().get_0().get_0());
        IdentityPublicKeyV0 ipkv0 = identity.getV0().getPublicKey(0);
        KeyID keyId = ipkv0.getId();
        assertEquals(1, keyId.get_0());
        assertEquals(false, ipkv0.getRead_only());
        //assertEquals(KeyType.KeyType_FFI_BLS12_381, ipkv0.getKey_type());
        assertEquals(Purpose.Purpose_FFI_AUTHENTICATION, ipkv0.getPurpose());

        IdentityPublicKeyV0 identityPublicKeyV0ById = identity.getV0().getPublicKeyById(1);
        assertEquals(ipkv0.getData().get_0().length, identityPublicKeyV0ById.getData().get_0().length);
        assertArrayEquals(ipkv0.getData().get_0(), identityPublicKeyV0ById.getData().get_0());

    }
}
