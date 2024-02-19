package org.dash.sdk;

import org.junit.jupiter.api.Test;

public class ContractBoundsTest extends BaseTest {
    @Test
    public void createContractBoundsInRustAndDestroy() {
        Identifier idRaw = example.identifierCtor(example.identifierBytes32Ctor(identifier));
        ContractBounds singleContractRaw = example.contractBoundsSingleContractCtor(idRaw);
        example.contractBoundsDestroy(singleContractRaw); // no crash
    }

    @Test
    public void createContractBoundsInRustAndDestroy2() {
        Identifier idRaw = example.identifierCtor(example.identifierBytes32Ctor(identifier));
        ContractBounds singleContractRaw = example.contractBoundsSingleContractDocumentTypeCtor(idRaw, "type");
        // example.contractBoundsDestroy(singleContractRaw); // crash
    }
    @Test
    public void createContractBoundsInJavaAndDestroy() {
        Identifier id = new Identifier(identifier);
        ContractBounds singleContract = ContractBounds.singleContract(id);
        singleContract.delete(); // doesn't delete the rust object because singleContract does not own it
        //example.contractBoundsDestroy(singleContract); // crash
    }

    @Test
    public void createContractBoundsInJavaAndDestroy2() {
        Identifier id = new Identifier(identifier);
        ContractBounds singleContract = ContractBounds.singleContractDocumentType(id, "type");
        singleContract.delete(); // doesn't delete the rust object because singleContract does not own it
        // example.contractBoundsDestroy(singleContract); // crash
    }
}
