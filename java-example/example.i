%module example

%{
extern "C" {
#include "../../../../ferment-example/target/example.h"
}
#include <stdlib.h>
#include "dpp.h"

MemoryFactory * MemoryFactory::instance = new MemoryFactory();
MemoryFactory & memoryFactory = *MemoryFactory::getInstance();
%}
//%include "enumsimple.swg"
//%rename("%(strip:[ffi_])s") "";
//%rename("%(regex:/^(ffi_)?(.+)/\\2/;s/_(.)/\\U\\1/g)sn") "";
//%rename(renameCamelCase) "";
//%rename("renameCamelCase") "^(ffi_)?(.+)$";
//%rename("%(camelcase)s",%$isfunction) "";
%ignore IdentityFactory_TraitObject::object;
%ignore IdentityFactory_TraitObject::vtable;
%ignore IdentityFactory_VTable;
%ignore IHaveChainSettings_TraitObject::object;
%ignore IHaveChainSettings_TraitObject::vtable;
%ignore IHaveChainSettings_VTable;

%ignore TestStruct_ctor(struct Vec_u8 *vec_u8,
                                           struct Vec_u32 *vec_u32,
                                           struct Vec_Vec_u32 *vec_vec_u32,
                                           struct std_collections_Map_keys_u32_values_u32 *map_key_simple_value_simple,
                                           struct std_collections_Map_keys_u32_values_crate_nested_HashID *map_key_simple_value_complex,
                                           struct std_collections_Map_keys_u32_values_Vec_u32 *map_key_simple_value_vec_simple,
                                           struct std_collections_Map_keys_u32_values_Vec_crate_nested_HashID *map_key_simple_value_vec_complex,
                                           struct std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_u32 *map_key_simple_value_map_key_simple_value_simple,
                                           struct std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_crate_nested_HashID *map_key_simple_value_map_key_simple_value_complex,
                                           struct std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_Vec_u32 *map_key_simple_value_map_key_simple_value_vec_simple,
                                           struct std_collections_Map_keys_u32_values_std_collections_Map_keys_u32_values_Vec_crate_nested_HashID *map_key_simple_value_map_key_simple_value_vec_complex,
                                           struct std_collections_Map_keys_crate_nested_HashID_values_u32 *map_key_complex_value_simple,
                                           struct std_collections_Map_keys_crate_nested_HashID_values_crate_nested_HashID *map_key_complex_value_complex,
                                           struct std_collections_Map_keys_crate_nested_HashID_values_Vec_u32 *map_key_complex_value_vec_simple,
                                           struct std_collections_Map_keys_crate_nested_HashID_values_Vec_crate_nested_HashID *map_key_complex_value_vec_complex,
                                           struct std_collections_Map_keys_crate_nested_HashID_values_std_collections_Map_keys_u32_values_Vec_u32 *map_key_complex_value_map_key_simple_value_vec_simple,
                                           struct std_collections_Map_keys_crate_nested_HashID_values_std_collections_Map_keys_u32_values_Vec_crate_nested_HashID *map_key_complex_value_map_key_simple_value_vec_complex,
                                           struct std_collections_Map_keys_crate_nested_HashID_values_std_collections_Map_keys_u32_values_std_collections_Map_keys_crate_nested_HashID_values_crate_nested_HashID *map_key_complex_value_map_key_simple_value_map_key_complex_value_complex,
                                           uint8_t opt_primitive,
                                           char *opt_string,
                                           struct Vec_u8 *opt_vec_primitive,
                                           struct Vec_String *opt_vec_string,
                                           struct Vec_crate_nested_HashID *opt_vec_complex,
                                           struct Vec_Vec_crate_nested_HashID *opt_vec_vec_complex);
%nodefaultctor;

%rename("%(lowercamelcase)s", %$isfunction) "";

%ignore Vec_u8;

//%ignore IdentifierBytes32;
//%rename("%(lowercamelcase)s") "";
%include "stdint.i"
%include "arrays_java.i"
// %typemap(in) uint8_t (*_0)[32] {
//         if ((*jenv)->GetArrayLength(jenv, $input) != 32) {
//             SWIG_JavaThrowException(jenv, SWIG_JavaRuntimeException, "byte[] must have a length of 32");
//             return $null;
//         }
//         $1 = (uint8_t (*)[32]) (*jenv)->GetByteArrayElements(jenv, $input, 0);
// }
//
// %typemap(freearg) uint8_t (*_0)[32] {
//         (*jenv)->ReleaseByteArrayElements(jenv, $input, (jbyte *) $1, 0);
// }
//
// %typemap(jstype, memberout="_0") uint8_t (*_0)[32] "byte[]"
// %typemap(jstype, memberin="_0") uint8_t (*_0)[32] "byte[]"
//
// %typemap(jtype) uint8_t (*_0)[32] "byte[]"
// %typemap(jni) uint8_t (*_0)[32] "byte[]"
// %typemap(in) uint8_t (*_0)[32] %{
// $1 = ($type) $input;
// %}
// %typemap(out) uint8_t (*_0)[32] %{
// $result = $jenv->NewByteArray(32);
// $jenv->SetByteArrayRegion($result, 0, 32, (jbyte*)$1);
// %}

// %typemap(jstype) unsigned int * value "int"
// %typemap(javain) unsigned int * value "$javainput"
// %typemap(javaout) unsigned int * value {
// return (int) *$1;
// }

// %typemap(jstype) uint8_t * "byte[]"
// %typemap(javain) uint8_t * "$javainput"
// %typemap(javaout) uint8_t * {
// return (uint8_t) *$1;
// }

//%typemap(jni) uint8_t (*)[32] "$javaclassname.getPointer($jnicall)"
// %include "carrays.i"
// %typemap(jstype) uint8_t (*)[32] "byte[]"
// %typemap(jtype) uint8_t (*)[32] "byte[]"
// %typemap(in) uint8_t (*)[32] {
//   //if (JLENGTH($input) != 32) {
//   //  SWIG_JavaThrowException(jenv, SWIG_JavaArithmeticError, "Input array must have exactly 32 bytes.");
//   //  return $null;
//   //}
//   $1 = ($1_ltype)$input;
// }
// %typemap(out) uint8_t (*)[32] {
//   //if ($1 == 0) {
//   //  $result = 0; // null
//   //} else {
//   //  $result = $1;
//  // }
// }
// %typemap(javaout) uint8_t (*)[32] {
//   return $jnicall;
// }

%extend Identifier {
    Identifier(uint8_t (*byteArray)[32]) {
        IdentifierBytes32 * identifierBytes32 = IdentifierBytes32_ctor(byteArray);
        return Identifier_ctor(identifierBytes32);
    }

    ~Identifier() {
        printf("~Identifier(%x)\n", $self);
        memoryFactory.destroyItem($self->_0->_0);
        IdentifierBytes32_destroy($self->_0); // Deallocate the memory when the object is destroyed
        Identifier_destroy($self);
    }
}

%extend IdentifierBytes32 {
    IdentifierBytes32(uint8_t (*identifierBytes)[32]) {
        //struct IdentifierBytes32 * identifierBytes32 = (struct IdentifierBytes32*)calloc(1, sizeof(struct IdentifierBytes32));
        //identifierBytes32->_0 = (uint8_t (*)[32])calloc(1, sizeof(uint8_t[32]));
        //memcpy(identifierBytes32->_0, identifierBytes, sizeof(uint8_t[32]));
        return IdentifierBytes32_ctor(identifierBytes);
    }

    ~IdentifierBytes32() {
        printf("~IdentityBytes32(%x)\n", $self);
        memoryFactory.destroyItem($self->_0);
        IdentifierBytes32_destroy($self);
    }
};

// uint8_t [] to byte []
%typemap(jni) (uint8_t (*)[32]) "jbyteArray"
%typemap(jtype) (uint8_t (*)[32]) "byte[]"
%typemap(jstype) (uint8_t (*)[32]) "byte[]"
%typemap(in) (uint8_t (*)[32]) (uint8_t * byteArray) {
  //$1 = (uint8_t (*)[32]) JCALL2(GetByteArrayElements, jenv, $input, 0);
    if (!$input) {
      SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, "null array");
      return $null;
    }
    const jsize sz = JCALL1(GetArrayLength, jenv, $input);
    jbyte* const jarr = JCALL2(GetByteArrayElements, jenv, $input, 0);
    if (!jarr) return $null;
    byteArray = (uint8_t *)memoryFactory.alloc(32); //calloc(1, 32); // this is a memory leak?
    memcpy(byteArray, jarr, sz);

    //memcpy($1, jarr, sz);
    JCALL3(ReleaseByteArrayElements, jenv, $input, jarr, JNI_ABORT);
    $1 = (uint8_t (*) [32])byteArray;
}
%typemap(out) (uint8_t (*)[32]) {
  $result = JCALL1(NewByteArray, jenv, 32);
  JCALL4(SetByteArrayRegion, jenv, $result, 0, 32, (jbyte *)(*$1));
}
%typemap(argout) (uint8_t (*)[32]) {
  //JCALL3(ReleaseByteArrayElements, jenv, $input, (jbyte *) *$1, 0);

}

%typemap(javain) (uint8_t (*)[32]) "$javainput"
%typemap(javaout) (uint8_t (*)[32]) {
    return $jnicall;
  }
%typemap(freearg) (uint8_t (*)[32]) ""


%extend KeyID {
    KeyID(uint32_t id) {
        return KeyID_ctor(id);
    }
    ~KeyID() {
        printf("~KeyID(%x)\n", $self);
        KeyID_destroy($self);
    }
}

%extend BinaryData {
    BinaryData(Vec_u8 *o_0) {
        return BinaryData_ctor(o_0);
    }
    ~BinaryData() {
        printf("~BinaryData(%lx)\n", $self);
        // memoryFactory.destroyItem($self->_0->values);
        // Vec_u8_destroy($self->_0);
        BinaryData_destroy($self);
    }
}

%extend ContractBounds {
    static ContractBounds * singleContract(Identifier * id) {
        return ContractBounds_SingleContract_ctor(id);
    }
    static ContractBounds * singleContract(Identifier * id, char * type) {
        return ContractBounds_SingleContractDocumentType_ctor(id, type);
    }
    ~ContractBounds() {
        ContractBounds_destroy($self);
    }
};

%ignore IdentityV0::public_keys;
%extend IdentityV0 {
    ~IdentityV0() {
        printf("~IdentityV0(%x)\n", $self);
        IdentityV0_destroy($self);
    }

    struct IdentityPublicKeyV0 * getPublicKey(uint32_t index) {
        return $self->public_keys->values[index]->v0;
    }

    struct IdentityPublicKeyV0 * getPublicKeyById(uint32_t id) {
        for (int i = 0; i < $self->public_keys->count; ++i) {
            if ($self->public_keys->keys[i]->_0 == id)
                return $self->public_keys->values[i]->v0;
        }
        return NULL;
    }
}

%ignore IdentityPublicKeyV0::key_type;
%ignore IdentityPublicKeyV0::purpose;
%ignore IdentityPublicKeyV0::security_level;

// struct IdentityPublicKeyV0 *IdentityPublicKeyV0_ctor(struct KeyID *id,
//                                                      enum Purpose *purpose,
//                                                      enum SecurityLevel *security_level,
//                                                      struct ContractBounds *contract_bounds,
//                                                      enum KeyType *key_type,
//                                                      bool read_only,
//                                                      struct BinaryData *data,
//                                                     struct TimestampMillis *disabled_at);

%extend IdentityPublicKeyV0 {
    IdentityPublicKeyV0(KeyID * keyId, Purpose purpose, SecurityLevel securityLevel,
        ContractBounds contract_bounds,
        KeyType key_type, bool read_only, BinaryData * data,
        TimestampMillis * disabled_at) {
        printf("new_IdentityPublicKeyV0 purpose %d\n", purpose);
         Purpose * purposeObject;
         switch(purpose) {
             case Purpose_AUTHENTICATION:
                 purposeObject = Purpose_AUTHENTICATION_ctor();
                 break;
             case Purpose_DECRYPTION:
                 purposeObject = Purpose_DECRYPTION_ctor();
                 break;
             case Purpose_ENCRYPTION:
                purposeObject = Purpose_ENCRYPTION_ctor();
                break;
            case Purpose_WITHDRAW:
                purposeObject = Purpose_WITHDRAW_ctor();
                break;
            case Purpose_SYSTEM:
                purposeObject = Purpose_SYSTEM_ctor();
                break;
            case Purpose_VOTING:
                purposeObject = Purpose_VOTING_ctor();
                break;
         }
        printf("  %lx\n", purposeObject);
        KeyType * keyTypeObject = intToKeyType(key_type);
        SecurityLevel * securityLevelObject = intToSecurityLevel(securityLevel);
        uint8_t * byteArray = (uint8_t*)memoryFactory.alloc(data->_0->count);
        memcpy(byteArray, data->_0->values, data->_0->count);
        Vec_u8 * vec_u8 = Vec_u8_ctor(data->_0->count, byteArray);
        BinaryData * binaryData = BinaryData_ctor(vec_u8);
        printf("  ->data(%lx)\n", binaryData);
        printf("  ->data->_0(%lx)\n", binaryData->_0);
        printf("  ->data->_0->values(%lx)\n", binaryData->_0->values);
        KeyID * keyIdObject = KeyID_ctor(keyId->_0);
        IdentityPublicKeyV0 * ipkv0 = IdentityPublicKeyV0_ctor(keyIdObject, purposeObject, securityLevelObject,
            &contract_bounds, keyTypeObject, read_only, binaryData, disabled_at);
        printf("IdentityPublicKeyV0(%x)\n", ipkv0);
        return ipkv0;
    }
    ~IdentityPublicKeyV0() {
        printf("~IdentityPublicKeyV0(%lx)\n", (unsigned long)$self);
        printf("  ->purpose(%x)\n", $self->purpose);
        Purpose_destroy($self->purpose);
        SecurityLevel_destroy($self->security_level);
        KeyType_destroy($self->key_type);
        KeyID_destroy($self->id);
        printf("  ->data->_0(%lx)\n", $self->data->_0);
        printf("  ->data->_0->values(%lx)\n", $self->data->_0->values);
        memoryFactory.destroyItem($self->data->_0->values);
        Vec_u8_destroy($self->data->_0);
        printf("  ->data(%lx)\n", $self->data);
        BinaryData_destroy($self->data); // crash
        TimestampMillis_destroy($self->disabled_at);
        IdentityPublicKeyV0_destroy($self); //crash
    }
    enum KeyType getKeyType() {
        return *$self->key_type;
    }
    enum Purpose getPurpose() {
        return *$self->purpose;
    }
    enum Purpose * getPurpose2() {
        return $self->purpose;
    }
    enum SecurityLevel getSecurityLevel() {
        return *self->security_level;
    }
}

%extend HashID {
    HashID(uint8_t (*o_0)[32]) {
        return HashID_ctor(o_0);
    }
    ~HashID() {
        printf("~HashID(%x)\n", $self);
        memoryFactory.destroyItem($self->_0);
        HashID_destroy($self);
    }
}

// %ignore ChainType_Tag;
%include "Vec_u8.i"


extern "C" {
%include "../ferment-example/target/example.h"
}
%include "src/main/c/dpp.h"
