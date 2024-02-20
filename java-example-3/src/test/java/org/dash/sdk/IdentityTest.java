package org.dash.sdk;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IdentityTest extends BaseTest {
    @Test
    public void getAnIdentityTest() {
        Identity identity = example.getAnIdentity();
        assertEquals(Identity_Tag.Identity_V0, identity.getTag());
        assertEquals(2, identity.getV0().getBalance());
        assertEquals(1, identity.getV0().getRevision().toLong());
        identity.delete(); // identity doesn't own the rust object
        // example.identityDestroy(identity); // crash in Identity::drop
    }
    @Test
    public void basicIdentityInRustAndDestroy() {
        Identity identity = example.createBasicIdentityV0(identifier);
        assertEquals(Identity_Tag.Identity_V0, identity.getTag());
        IdentityV0 identityV0 = identity.getV0();
        assertNotNull(identityV0);
        assertArrayEquals(identifier, identityV0.getId().get_0().get_0());
        assertEquals(0L, identityV0.getRevision().toLong());
        assertEquals(0L, identityV0.getBalance());
        assertNull(identityV0.getPublicKey(0));
        example.identityDestroy(identity);
    }

    @Test
    public void getIdentityTest() {
        Identifier identifier = new Identifier(contractIdentifier);
        Identity identity = example.getIdentity2(identifier);
        assertFalse(identity.swigCMemOwn);
        assertEquals(Identity_Tag.Identity_V0, identity.getTag());
        assertEquals(2, identity.getV0().getBalance());
        assertEquals(1, identity.getV0().getRevision().toLong());
        assertNotNull(identity.getV0().getId().get_0().get_0());
        assertArrayEquals(identifier.get_0().get_0(), identity.getV0().getId().get_0().get_0());
        IdentityPublicKeyV0 ipkv0 = identity.getV0().getPublicKey(0);
        int keyId = ipkv0.getId().toInt();
        assertEquals(1, keyId);
        assertFalse(ipkv0.getRead_only());
        // assertEquals(KeyType.KeyType_BLS12_381, ipkv0.getKey_type());
        assertEquals(Purpose.Purpose_AUTHENTICATION, ipkv0.getPurpose());
        assertEquals(SecurityLevel.SecurityLevel_MASTER, ipkv0.getSecurityLevel());
        assertEquals(KeyType.KeyType_ECDSA_SECP256K1, ipkv0.getKeyType());
        // assertEquals(0, ipkv0.getDisabled_at().get_0().longValue());

        IdentityPublicKeyV0 identityPublicKeyV0ById = identity.getV0().getPublicKeyById(1);
        assertEquals(ipkv0.getData().get_0().length, identityPublicKeyV0ById.getData().get_0().length);
        assertArrayEquals(ipkv0.getData().get_0(), identityPublicKeyV0ById.getData().get_0());
        // this crashes the system, it was created in Rust
        // this crashes with ContractBounds::drop
        // example.identityDestroy(identity);
    }

    @Test
    public void getIdentityFromRustAndDestroyTest() {
        Identifier identifier = new Identifier(contractIdentifier);
        Identity identity = example.createBasicIdentityV0(identifier.get_0().get_0());
        example.identityDestroy(identity);

        Identity identity2 = example.getIdentity2(identifier);
        //assertNull(identity2.getV0().getPublicKey(0).getContract_bounds()); //crash accessing ContractBounds
        //example.identityDestroy(identity2); // crash on destroying contract bounds

        identifier.delete();

    }
//
//    @Test
//    public void traitTest() {
//
//        ChainType type = example.ffiGetChainSettings();
//        IHaveChainSettings_TraitObject trait = example.chainTypeAsIHaveChainSettingsTraitObject(type);
//        IHaveChainSettings ihcs = new IHaveChainSettings(trait);
//        assertEquals(0, ihcs.genesisHeight());
//        assertEquals(0, ihcs.genesisHash().get_0()[1]);
//
//        byte[] hash = new byte[32];
//        for (int i = 0; i < 32; ++i) {
//            hash[i] = (byte) i;
//        }
//        HashID hashID = new HashID(hash);
//        assertArrayEquals(hashID.get_0(), hash);
//
//        MyIdentityFactory myFactory = example.ffiGetIdentityFactory();
//        IdentityFactory_TraitObject traitObject = example.myIdentityFactoryAsIdentityFactoryTraitObject(myFactory);
//        IdentityFactory factory = new IdentityFactory(traitObject);
//        Identity identity = factory.getIdentity(new Identifier(new byte[32]));
//        assertArrayEquals(new byte[32], identity.getV0().getId().get_0().get_0());
//
//        IdentityPublicKeyV0 ipkv0 = identity.getV0().getPublicKey(0);
//        KeyID keyId = ipkv0.getId();
//        assertEquals(1, keyId.get_0());
//        assertFalse(ipkv0.getRead_only());
//        assertEquals(Purpose.Purpose_AUTHENTICATION, ipkv0.getPurpose());
//
//        IdentityPublicKeyV0 identityPublicKeyV0ById = identity.getV0().getPublicKeyById(1);
//        assertEquals(ipkv0.getData().get_0().length, identityPublicKeyV0ById.getData().get_0().length);
//        assertArrayEquals(ipkv0.getData().get_0(), identityPublicKeyV0ById.getData().get_0());
//    }


//    @Test
//    public void asyncFunctionTest() {
//        ChainType mainNet = example.chainTypeMainNetCtor();
//        assertEquals("ChainType_MainNet", mainNet.getTag().toString());
//        //ChainType chainType = new ChainType();
//        //example.
//        SWIGTYPE_p_void p_void = new SWIGTYPE_p_void();
//        //String result = example.ffiGetChainTypeStringAsync(p_void, mainNet);
//    }
}
