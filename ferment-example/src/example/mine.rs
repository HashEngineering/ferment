
#[ferment_macro::export]
pub struct VecU8Holder {
    pub first: Vec<u8>
}
#[ferment_macro::export]
pub struct InnerStruct {
    pub first: u64,
    pub second: u64
}
#[ferment_macro::export]
pub struct OuterStruct {
    pub first: InnerStruct,
    pub second: InnerStruct
}

#[ferment_macro::export]
pub fn create_outer(is1: InnerStruct, is2: InnerStruct) -> OuterStruct {
    OuterStruct {
        first: is1,
        second: is2,
    }
}