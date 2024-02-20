package org.dash.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContractBoundsTest extends BaseTest {
    // TODO: these constructors do not work
    @Test
    public void createSingleContractInRustAndDestroy() {
        Identifier idRaw = example.identifierCtor(example.identifierBytes32Ctor(identifier));
        ContractBounds singleContractRaw = example.contractBoundsSingleContractCtor(idRaw);
        assertEquals(ContractBounds_Tag.ContractBounds_SingleContract, singleContractRaw.getTag());
        assertArrayEquals(identifier, singleContractRaw.getSingle_contract_document_type().getId().get_0().get_0());
        example.contractBoundsDestroy(singleContractRaw); // no crash
    }
//
//    @Test
//    public void createSingleContractDocumentTypeInRustAndDestroy() {
//        Identifier idRaw = example.identifierCtor(example.identifierBytes32Ctor(identifier));
//        ContractBounds singleContractRaw = example.contractBoundsSingleContractDocumentTypeCtor(idRaw, "type");
//        assertEquals(ContractBounds_Tag.ContractBounds_SingleContractDocumentType, singleContractRaw.getTag());
//        assertArrayEquals(identifier, singleContractRaw.getSingle_contract_document_type().getId().get_0().get_0());
//        assertEquals("type", singleContractRaw.getSingle_contract_document_type().getDocument_type_name());
//        // example.contractBoundsDestroy(singleContractRaw); // crash
//    }
//    @Test
//    public void createSingleContractInJavaAndDestroy() {
//        Identifier id = new Identifier(identifier);
//        ContractBounds singleContract = ContractBounds.singleContract(id);
//        assertEquals(ContractBounds_Tag.ContractBounds_SingleContract, singleContract.getTag());
//        assertArrayEquals(identifier, singleContract.getSingle_contract_document_type().getId().get_0().get_0());
//        singleContract.delete(); // doesn't delete the rust object because singleContract does not own it
//        // example.contractBoundsDestroy(singleContract); // crash
//        id.delete();
//
//        ContractBounds contractBounds = new ContractBounds(ContractBounds.getCPtr(example.contractBoundsSingleContractCtor(id)), true);
//        //contractBounds.delete();
//    }
    @Test
    public void createSingleContractInJavaAndDestroy2() {
        Identifier id = new Identifier(identifier);
        ContractBounds singleContract = new ContractBounds(id);
        assertEquals(ContractBounds_Tag.ContractBounds_SingleContract, singleContract.getTag());
        assertArrayEquals(identifier, singleContract.getSingle_contract_document_type().getId().get_0().get_0());
        singleContract.delete(); // doesn't delete the rust object because singleContract does not own it
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

    @Test
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
