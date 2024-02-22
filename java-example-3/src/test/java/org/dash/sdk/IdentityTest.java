package org.dash.sdk;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IdentityTest extends BaseTest {
    @Test
    public void getAnIdentityTest() {
        byte [] identifierBytes = new byte[] {
                65, 63, 57, (byte)243, (byte)204, 9, 106, 71, (byte)187, 2, 94, (byte)221, (byte)190, 127, (byte)141, 114,(byte) 137, (byte)209, (byte)243, 50,
                60, (byte)215, 90, 101, (byte)229, 15, 115, 5, 44, 117, (byte)182, (byte)217
        };
        Identity identity = example.getAnIdentity();
        assertNotNull(identity);
        assertTrue(identity.swigCMemOwn);
        assertEquals(Identity_Tag.Identity_V0, identity.getTag());
        assertEquals(0, identity.getV0().getPublicKeyCount());
        assertNull(identity.getV0().getPublicKey(0));
        assertNull(identity.getV0().getPublicKeyById(1));
        assertEquals(2, identity.getV0().getBalance());
        assertEquals(1, identity.getV0().getRevision().toLong());
        assertArrayEquals(identifierBytes, identity.getV0().getId().get_0().get_0());
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
        assertNotNull(identity);
        assertTrue(identity.swigCMemOwn);
        assertEquals(Identity_Tag.Identity_V0, identity.getTag());
        IdentityV0 identityV0 = identity.getV0();

        assertEquals(2, identityV0.getBalance());
        assertEquals(1, identityV0.getRevision().toLong());
        assertEquals(2, identityV0.getPublicKeyCount());

        assertNotNull(identity.getV0().getId().get_0().get_0());
        assertArrayEquals(identifier.get_0().get_0(), identity.getV0().getId().get_0().get_0());

        for (int i = 0; i < identityV0.getPublicKeyCount(); ++i) {
            IdentityPublicKeyV0 ipkv0 = identityV0.getPublicKey(i);
            assertEquals(1, ipkv0.getId().toInt());
            assertFalse(ipkv0.getRead_only());
            assertEquals(Purpose.Purpose_AUTHENTICATION, ipkv0.getPurpose());
            assertEquals(SecurityLevel.SecurityLevel_MASTER, ipkv0.getSecurityLevel());
            assertEquals(KeyType.KeyType_ECDSA_SECP256K1, ipkv0.getKeyType());
            assertTrue(ipkv0.getDisabled_at().isNull());
            assertEquals(33, ipkv0.getData().get_0().length);
            assertTrue(ipkv0.getContract_bounds().isNull());
        }

        IdentityPublicKeyV0 ipkv0 = identityV0.getPublicKey(0);
        IdentityPublicKeyV0 identityPublicKeyV0ById = identity.getV0().getPublicKeyById(1);
        assertEquals(ipkv0.getData().get_0().length, identityPublicKeyV0ById.getData().get_0().length);
        assertArrayEquals(ipkv0.getData().get_0(), identityPublicKeyV0ById.getData().get_0());
        // this crashes the system, it was created in Rust
        // this crashes with ContractBounds::drop
        // identity.delete();
    }

    @Test
    public void getIdentityWithBoundsFromRustAndDestroyTest() {
        Identifier id = new Identifier(identifier);
        Identifier idContract = new Identifier(contractIdentifier);
        Identity identityWithBounds = example.getIdentityContractBounds(id, idContract);
        assertNotNull(identityWithBounds);
        assertTrue(identityWithBounds.swigCMemOwn);
        assertEquals(Identity_Tag.Identity_V0, identityWithBounds.getTag());
        IdentityV0 identityV0 = identityWithBounds.getV0();

        assertEquals(2, identityV0.getBalance());
        assertEquals(1, identityV0.getRevision().toLong());
        assertEquals(2, identityV0.getPublicKeyCount());

        assertNotNull(identityWithBounds.getV0().getId().get_0().get_0());
        assertArrayEquals(identifier, identityWithBounds.getV0().getId().get_0().get_0());

        for (int i = 0; i < identityV0.getPublicKeyCount(); ++i) {
            IdentityPublicKeyV0 ipkv0 = identityV0.getPublicKey(i);
            assertEquals(1, ipkv0.getId().toInt());
            assertFalse(ipkv0.getRead_only());
            assertEquals(Purpose.Purpose_AUTHENTICATION, ipkv0.getPurpose());
            assertEquals(SecurityLevel.SecurityLevel_MASTER, ipkv0.getSecurityLevel());
            assertEquals(KeyType.KeyType_ECDSA_SECP256K1, ipkv0.getKeyType());
            assertFalse(ipkv0.getDisabled_at().isNull());
            assertEquals(33, ipkv0.getData().get_0().length);
            assertFalse(ipkv0.getContract_bounds().isNull());
            assertEquals(ContractBounds_Tag.ContractBounds_SingleContract, ipkv0.getContract_bounds().getTag());
            assertArrayEquals(contractIdentifier, ipkv0.getContract_bounds().getSingle_contract_document_type().getId().get_0().get_0());
        }

        IdentityPublicKeyV0 ipkv0 = identityV0.getPublicKey(0);
        IdentityPublicKeyV0 identityPublicKeyV0ById = identityWithBounds.getV0().getPublicKeyById(1);
        assertEquals(ipkv0.getData().get_0().length, identityPublicKeyV0ById.getData().get_0().length);
        assertArrayEquals(ipkv0.getData().get_0(), identityPublicKeyV0ById.getData().get_0());
        // this crashes the system, it was created in Rust
        // this crashes with ContractBounds::drop
        identityWithBounds.delete();
        id.delete();
        idContract.delete();
    }

    @Test
    public void createBasicIdentityInRustAndDestroyTest() {
        Identifier identifier = new Identifier(contractIdentifier);
        Identity identity = example.createBasicIdentityV0(identifier.get_0().get_0());
        assertNotNull(identity);
        assertTrue(identity.swigCMemOwn);
        assertEquals(Identity_Tag.Identity_V0, identity.getTag());
        IdentityV0 identityV0 = identity.getV0();
        assertArrayEquals(contractIdentifier, identityV0.getId().get_0().get_0());
        assertEquals(0, identityV0.getRevision().toLong());
        assertEquals(0, identityV0.getBalance());
        assertEquals(0, identityV0.getPublicKeyCount());
        example.identityDestroy(identity);
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

    @Test
    public void createAnIdentityInJavaAndDestroy() {
        Identity identity = new Identity();
        identity.delete();
    }
}
