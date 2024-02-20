%module example

%{
extern "C" {
#include "../../../../ferment-example/target/example.h"
}
#include <stdlib.h>
#include "dpp.h"
#include <ctime>

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
// %include "arrays_java.i"



// TODO: need to add objectEquals to all classes
// %typemap(javacode) SWIGTYPE %{
//   public boolean equals(Object obj) {
//     boolean equal = false;
//     if (obj instanceof $javaclassname) {
//       equal = ((($javaclassname)obj).swigCPtr == this.swigCPtr) || objectEquals(($javaclassname)obj);
//     }
//     return equal;
//   }
//   public int hashCode() {
//     return (int)swigCPtr;
//   }
// %}

%include "binary_data.i"
%include "contract_bounds.i"
%include "identifier.i"
%include "identity.i"
%include "identity_public_key.i"
%include "identity_public_key_enums.i"
%include "keyid.i"
%include "revision.i"
%include "uint8_array.i"
%include "timestamp_millis.i"
%include "enums.i"


%ignore HashID;
// %extend HashID {
//     HashID(uint8_t (*o_0)[32]) {
//         return HashID_ctor(o_0);
//     }
//     ~HashID() {
//         printf("~HashID(%lx)\n", $self);
//         memoryFactory.destroyItem($self->_0);
//         HashID_destroy($self);
//     }
// }

%extend VecU8Holder {
    VecU8Holder(Vec_u8 * vec_u8) {
        return VecU8Holder_ctor(vec_u8);
    }

    ~VecU8Holder() {
        //memoryFactory.destroyItem($self->first->values); //above
        VecU8Holder_destroy($self); // causes crash with above, no crash if this is the only line, order doesn't matter
        //memoryFactory.destroyItem($self->first->values); //above
    }
}

%extend InnerStruct {
    InnerStruct(long a, long b) {
        return InnerStruct_ctor(a, b);
    }

    ~InnerStruct() {
        InnerStruct_destroy($self);
    }
}

%extend OuterStruct {
    OuterStruct(InnerStruct * is1, InnerStruct * is2) {
        OuterStruct * os = create_outer(is1, is2);
        printf("is1 (%lx)\n", (uint64_t)is1);
        printf("is2 (%lx)\n", (uint64_t)is2);
        printf("os  (%lx)\n", (uint64_t)os);
        printf("os->is1 (%lx)\n", (uint64_t)os->first);
        printf("os->is2 (%lx)\n", (uint64_t)os->second);
        return os;
    }
    OuterStruct(long a, long b, long c, long d) {
        InnerStruct * is1 = InnerStruct_ctor(a, b);
        InnerStruct * is2 = InnerStruct_ctor(c, d);
        OuterStruct * os = OuterStruct_ctor(is1, is2);
        printf("is1 (%lx)\n", (uint64_t)is1);
        printf("is2 (%lx)\n", (uint64_t)is2);
        printf("os  (%lx)\n", (uint64_t)os);
        printf("os->is1 (%lx)\n", (uint64_t)os->first);
        printf("os->is2 (%lx)\n", (uint64_t)os->second);
        return os;
    }
    ~OuterStruct() {
        //InnerStruct * first = $self->first;
        OuterStruct_destroy($self);
        //InnerStruct_destroy(first);
    }
}



// %ignore ChainType_Tag;
%include "Vec_u8.i"


extern "C" {
%include "../ferment-example/target/example.h"
}
%include "src/main/c/dpp.h"
