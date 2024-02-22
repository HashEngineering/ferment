package org.dash.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContractBoundsTest extends BaseTest {
    // TODO: these constructors do not work
//    @Test
//    public void createSingleContractInRustAndDestroy() {
//        Identifier idRaw = example.identifierCtor(example.identifierBytes32Ctor(identifier));
//        ContractBounds singleContractRaw = example.contractBoundsSingleContractCtor(idRaw);
//        assertEquals(ContractBounds_Tag.ContractBounds_SingleContract, singleContractRaw.getTag());
//        assertArrayEquals(identifier, singleContractRaw.getSingle_contract_document_type().getId().get_0().get_0());
//        example.contractBoundsDestroy(singleContractRaw); // no crash
//    }
//
//    @Test
//    public void createSingleContractInRustAndDestroy2() {
//        Identifier idRaw = example.identifierCtor(example.identifierBytes32Ctor(identifier));
//        ContractBounds singleContractRaw = example.contractBoundsSingleContractCtor(idRaw);
//        assertEquals(ContractBounds_Tag.ContractBounds_SingleContract, singleContractRaw.getTag());
//        assertArrayEquals(identifier, singleContractRaw.getSingle_contract_document_type().getId().get_0().get_0());
//        //example.contractBoundsDestroy(singleContractRaw); // no crash
//        singleContractRaw.delete();
//    }

//    @Test
//    public void createSingleContractDocumentTypeInRustAndDestroy() {
//        Identifier idRaw = example.identifierCtor(example.identifierBytes32Ctor(identifier));
//        ContractBounds singleContractRaw = example.contractBoundsSingleContractDocumentTypeCtor(idRaw, "type");
//        assertEquals(ContractBounds_Tag.ContractBounds_SingleContractDocumentType, singleContractRaw.getTag());
//        assertArrayEquals(identifier, singleContractRaw.getSingle_contract_document_type().getId().get_0().get_0());
//        // assertEquals("type", singleContractRaw.getSingle_contract_document_type().getDocument_type_name());
//        // example.contractBoundsDestroy(singleContractRaw); // crash
//    }
//
//    @Test
//    public void createSingleContractDocumentTypeInRustAndDestroy2() {
//        Identifier idRaw = example.identifierCtor(example.identifierBytes32Ctor(identifier));
//        ContractBounds singleContractRaw = example.contractBoundsSingleContractDocumentTypeCtor(idRaw, "type");
//        assertEquals(ContractBounds_Tag.ContractBounds_SingleContractDocumentType, singleContractRaw.getTag());
//        assertArrayEquals(identifier, singleContractRaw.getSingle_contract_document_type().getId().get_0().get_0());
//        // assertEquals("type", singleContractRaw.getSingle_contract_document_type().getDocument_type_name());
//        // example.contractBoundsDestroy(singleContractRaw); // crash
//        // singleContractRaw.delete(); crash
//    }
    @Test
    public void createSingleContractInJavaAndDestroy() {
        Identifier id = new Identifier(identifier);
        ContractBounds singleContractA = new ContractBounds(id);
        singleContractA.delete();


        ContractBounds singleContract = example.singleContract(id);
        assertEquals(ContractBounds_Tag.ContractBounds_SingleContract, singleContract.getTag());
        assertArrayEquals(identifier, singleContract.getSingle_contract_document_type().getId().get_0().get_0());
        //singleContract.delete(); // doesn't delete the rust object because singleContract does not own it
        //ContractBounds contractBounds2 = new ContractBounds(ContractBounds.getCPtr(singleContract), true);
        //contractBounds2.delete();
        singleContract.delete();
        // example.contractBoundsDestroy(singleContract); // crash
        id.delete();

        //ContractBounds contractBounds = new ContractBounds(ContractBounds.getCPtr(example.contractBoundsSingleContractCtor(id)), true);
        //contractBounds.delete();
    }

    @Test
    public void createSingleContractInRustAndTakeOwnershipInJavaAndDestroy() {
        Identifier id = new Identifier(identifier);
        ContractBounds singleContract = example.singleContract(id); //ContractBounds.singleContract(id);
        assertEquals(ContractBounds_Tag.ContractBounds_SingleContract, singleContract.getTag());
        assertArrayEquals(identifier, singleContract.getSingle_contract_document_type().getId().get_0().get_0());
        singleContract.delete();
        //ContractBounds singleContractOwner = new ContractBounds(ContractBounds.getCPtr(singleContract), true);
        //singleContractOwner.delete();
        id.delete();
    }

    @Test
    public void createSingleContractInRustAndTakeOwnershipInJavaAndDestroy3() {
        Identifier id = new Identifier(identifier);
        ContractBounds singleContract = example.singleContract(id);
        assertEquals(ContractBounds_Tag.ContractBounds_SingleContract, singleContract.getTag());
        // singleContract.delete();
        assertArrayEquals(identifier, singleContract.getSingle_contract_document_type().getId().get_0().get_0());
        ContractBounds singleContractOwner = new ContractBounds(ContractBounds.getCPtr(singleContract), true);
        singleContractOwner.delete();
        id.delete();
    }

//    @Test
//    public void createSingleContract2InRustAndTakeOwnershipInJavaAndDestroy() {
//        Identifier id = new Identifier(identifier);
//        ContractBounds singleContract = ContractBounds.singleContract2(id);
//        assertEquals(ContractBounds_Tag.ContractBounds_SingleContract, singleContract.getTag());
//        assertArrayEquals(identifier, singleContract.getSingle_contract_document_type().getId().get_0().get_0());
//        //ContractBounds singleContractOwner = new ContractBounds(ContractBounds.getCPtr(singleContract), true);
//        //singleContractOwner.delete();
//        singleContract.delete();
//        id.delete();
//    }

    @Test
    public void createSingleContractInJavaAndDestroy2() {
        Identifier id = new Identifier(identifier);
        ContractBounds singleContract = new ContractBounds(id);
        assertEquals(ContractBounds_Tag.ContractBounds_SingleContract, singleContract.getTag());
        assertArrayEquals(identifier, singleContract.getSingle_contract_document_type().getId().get_0().get_0());
        singleContract.delete();
        id.delete();
    }

//    @Test
//    public void createSingleContractDocumentTypeInJavaAndDestroy() {
//        Identifier id = new Identifier(identifier);
//        ContractBounds singleContract = ContractBounds.singleContractDocumentType(id, "type");
//        assertEquals(ContractBounds_Tag.ContractBounds_SingleContractDocumentType, singleContract.getTag());
//        assertArrayEquals(identifier, singleContract.getSingle_contract_document_type().getId().get_0().get_0());
//        assertEquals("type", singleContract.getSingle_contract_document_type().getDocument_type_name());
//        singleContract.delete(); // doesn't delete the rust object because singleContract does not own it
//        // example.contractBoundsDestroy(singleContract); // crash
//        id.delete();
//    }

    // @Test
    public void createSingleContractDocumentTypeInJavaAndDestroy2() {
        Identifier id = new Identifier(identifier);
        ContractBounds singleContract = new ContractBounds(id, "type");
        assertEquals(ContractBounds_Tag.ContractBounds_SingleContractDocumentType, singleContract.getTag());
        assertArrayEquals(identifier, singleContract.getSingle_contract_document_type().getId().get_0().get_0());
        assertEquals("type", singleContract.getSingle_contract_document_type().getDocument_type_name());
        singleContract.delete(); // doesn't delete the rust object because singleContract does not own it
        id.delete();
    }
}
