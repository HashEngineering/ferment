%module example

%{
#include "../../../../ferment-example/target/example.h"
%}
%rename("%(strip:[ffi_])s") "";
%rename(HashID) HashID_FFI;
%rename(SecurityLevel) SecurityLevel_FFI;
%rename(KeyID) KeyID_FFI;
%rename(Revision) Revision_FFI;
%rename(Purpose) Purpose_FFI;
%rename(Identifier) Identifier_FFI;
//%ignore IdentifierBytes32_FFI;
%rename(IdentifierBytes32) IdentifierBytes32_FFI;

%rename(IdentityPublicKey) IdentityPublicKey_FFI;
%rename(IdentityPublicKeyTag) IdentityPublicKey_FFI_Tag;
%rename(IdentityPublicKeyV0) IdentityPublicKeyV0_FFI;
%rename(Identity) Identity_FFI;
%rename(IdentityV0) IdentityV0_FFI;
%rename(BinaryData) BinaryData_FFI;
%rename(KeyType) KeyType_FFI;
%rename(IntegerHashIDMap) Map_keys_u32_values_HashID_FFI;
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

// %extend IdentifierBytes32_FFI {
//     bool ownsMemory;
//
//     IdentifierBytes32_FFI() {
//         struct IdentifierBytes32_FFI * identifierBytes32 = (struct IdentifierBytes32_FFI*)calloc(1, sizeof(struct IdentifierBytes32_FFI));
//         identifierBytes32->_0 = (uint8_t(*)[32])malloc(32); // Allocate memory for _0
//         ownsMemory = true;
//         return identifierBytes32;
//     }
//
//     ~IdentifierBytes32_FFI() {
//         if (ownsMemory)
//             free($self->_0); // Deallocate the memory when the object is destroyed
//         free(self);
//     }
// };

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



// %naturalvar IdentifierBytes32_FFI;
// struct IdentifierBytes32_FFI;
// %feature("valuewrapper") IdentifierBytes32_FFI;
//
// %typemap(jni) IdentifierBytes32_FFI* "jbyteArray"
// %typemap(jtype) (IdentifierBytes32_FFI*) "byte[]"
// %typemap(jstype) (IdentifierBytes32_FFI*) "byte[]"
// %typemap(in) (IdentifierBytes32_FFI*) (jbyteArray byteArray) {
//   //$1 = (uint8_t (*)[32]) JCALL2(GetByteArrayElements, jenv, $input, 0);
//     if (!$input) {
//       SWIG_JavaThrowException(jenv, SWIG_JavaNullPointerException, "null array");
//       return $null;
//     }
//     const jsize sz = JCALL1(GetArrayLength, jenv, $input);
//     jbyte* const jarr = JCALL2(GetByteArrayElements, jenv, $input, 0);
//     if (!jarr) return $null;
//     memcpy(byteArray, jarr, sz);
//     JCALL3(ReleaseByteArrayElements, jenv, $input, jarr, JNI_ABORT);
//     $1 = (uint8_t (*)[32])byteArray;
// }
// %typemap(out) (IdentifierBytes32_FFI*) {
//   $result = JCALL1(NewByteArray, jenv, 32);
//   JCALL4(SetByteArrayRegion, jenv, $result, 0, 32, (jbyte *)($1));
// }
// %typemap(argout) (IdentifierBytes32_FFI*) {
//   JCALL3(ReleaseByteArrayElements, jenv, $input, (jbyte *) $1, 0);
// }
//
// %typemap(javain) (IdentifierBytes32_FFI*) "$javainput"
// %typemap(javaout) (IdentifierBytes32_FFI*) {
//     return $jnicall;
//   }
// %typemap(freearg) (IdentifierBytes32_FFI*) ""
//%typemap(typecheck) IdentifierBytes32_FFI = uint8_t (*)[32];



%include "../ferment-example/target/example.h"
