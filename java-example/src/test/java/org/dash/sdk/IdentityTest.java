package org.dash.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IdentityTest {
    static {
        System.loadLibrary("sdklib");
    }

    @Test
    public void getAnIdentityTest() {
        Identity identity = example.get_an_identity();
        assertEquals(Identity_FFI_Tag.Identity_FFI_V0, identity.getTag());
        assertEquals(2, identity.getV0().getBalance().longValue());
        assertEquals(1, identity.getV0().getRevision().get_0().longValue());
    }

    @Test
    public void getIdentityTest() {
        byte[] id = new byte[32];
        id[0] = 1;
        id[1] = 2;
        IdentifierBytes32 identifierBytes32 = new IdentifierBytes32();
        identifierBytes32.set_0(id);
        Identifier identifier = new Identifier();
        identifier.set_0(identifierBytes32);
        Identity identity = example.get_identity(identifier);
        assertEquals(Identity_FFI_Tag.Identity_FFI_V0, identity.getTag());
        assertEquals(2, identity.getV0().getBalance().longValue());
        assertEquals(1, identity.getV0().getRevision().get_0().longValue());
        assertNotNull(identity.getV0().getId().get_0().get_0());
        assertEquals(1, identity.getV0().getId().get_0().get_0()[0]);

    }
}
