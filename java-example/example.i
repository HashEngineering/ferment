%module example

%{
#include "../../../../ferment-example/target/example.h"
#include <stdlib.h>
%}
//%rename("%(strip:[ffi_])s") "";
//%rename("%(regex:/^(ffi_)?(.+)/\\2/;s/_(.)/\\U\\1/g)sn") "";
//%rename(renameCamelCase) "";
//%rename("renameCamelCase") "^(ffi_)?(.+)$";
//%rename("%(camelcase)s",%$isfunction) "";
%rename("%(lowercamelcase)s", %$isfunction) "";
%rename(HashID) HashID_FFI;
%rename(SecurityLevel) SecurityLevel_FFI;
%rename(KeyID) KeyID_FFI;
%rename(Revision) Revision_FFI;
%rename(Purpose) Purpose_FFI;
%rename(Identifier) Identifier_FFI;
//%ignore IdentifierBinaryData32_FFI;
%rename(IdentifierBinaryData32) IdentifierBinaryData32_FFI;

%rename(IdentityPublicKey) IdentityPublicKey_FFI;
%rename(IdentityPublicKeyTag) IdentityPublicKey_FFI_Tag;
%rename(IdentityPublicKeyV0) IdentityPublicKeyV0_FFI;
%rename(Identity) Identity_FFI;
%rename(IdentityV0) IdentityV0_FFI;
%rename(BinaryData) BinaryData_FFI;
%rename(KeyType) KeyType_FFI;
%rename(IntegerHashIDMap) Map_keys_u32_values_HashID_FFI;

%ignore IdentityPublicKeyV0_FFI::purpose;
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
//   //  SWIG_JavaThrowException(jenv, SWIG_JavaArithmeticError, "Input array must have exactly 32 BinaryData.");
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

%extend IdentifierBinaryData32_FFI {
    IdentifierBinaryData32_FFI(uint8_t identifierBinaryData[32]) {
        struct IdentifierBinaryData32_FFI * identifierBinaryData32 = (struct IdentifierBinaryData32_FFI*)calloc(1, sizeof(struct IdentifierBinaryData32_FFI));
        identifierBinaryData32->_0 = (uint8_t (*)[32])calloc(1, sizeof(uint8_t[32]));
        memcpy(identifierBinaryData32->_0, identifierBinaryData, sizeof(uint8_t[32]));
        return identifierBinaryData32;
    }

    ~IdentifierBinaryData32_FFI() {
        free($self->_0); // Deallocate the memory when the object is destroyed
        free(self);
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
    byteArray = calloc(1, 32); // this is a memory leak?
    memcpy(byteArray, jarr, sz);
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





%extend IdentityV0_FFI {
    struct IdentityPublicKeyV0_FFI * getPublicKey(uint32_t index) {
        return $self->public_keys->values[index]->v0;
    }

    struct IdentityPublicKeyV0_FFI * getPublicKeyById(uint32_t id) {
        for (int i = 0; i < $self->public_keys->count; ++i) {
            if($self->public_keys->keys[i]->_0 == id)
                return $self->public_keys->values[i]->v0;
        }
        return NULL;
    }
}

%extend IdentityPublicKeyV0_FFI {
    enum Purpose_FFI getPurpose() {
        return *$self->purpose;
    }
    void setPurpose(enum Purpose_FFI purpose) {

    }
}


// %naturalvar BinaryData;
//
// class BinaryData;
//
// %feature("valuewrapper") BinaryData;
//
// // BinaryData
// %typemap(jni) BinaryData "jbyteArray"
// %typemap(jtype) BinaryData "byte[]"
// %typemap(jstype) BinaryData "byte[]"
//
//
// %typemap(in) BinaryData
// %{
//     BinaryData $1_BinaryDataObject((const uint8_t *)jenv->GetByteArrayElements($input, 0), jenv->GetArrayLength($input));
//     $1 = $1_BinaryDataObject;
// %}
//
// %typemap(argout) BinaryData {
//     JCALL3(ReleaseByteArrayElements, jenv, $input, (jbyte *) $1.begin(), 0);
// }
//
// %typemap(out) BinaryData {
//     $result = JCALL1(NewByteArray, jenv, $1.size());
//    JCALL4(SetByteArrayRegion, jenv, $result, 0, $1.size(), (jbyte *) $1.begin());
// }
//
// %typemap(javain) BinaryData "$javainput"
//
// %typemap(javaout) BinaryData {
//     return $jnicall;
//   }
//
// %typemap(typecheck) BinaryData = char *;
//
// %typemap(throws) BinaryData
// %{ SWIG_JavaThrowException(jenv, SWIG_JavaRuntimeException, "null BinaryData");
//    return $null; %}


%include "../ferment-example/target/example.h"
