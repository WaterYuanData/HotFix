//
// Created by yuan on 2019/6/29.
//
#include <cstdint>

#ifndef HOTFIX_ART_H
#define HOTFIX_ART_H
namespace art {
    class ArtMethod {
    public:
        uint32_t declaring_class_;
        uint32_t access_flags_;
        uint32_t dex_code_item_offset_;
        uint32_t dex_method_index_;
        uint16_t method_index_;
        uint16_t hotness_count_;
        struct PtrSizedFields {
            ArtMethod **dex_cache_resolved_methods_;
            uint32_t *dex_cache_resolved_types_;
            void *entry_point_from_jni_;
            void *entry_point_from_quick_compiled_code_;
        } ptr_sized_fields_;
    };
} // namespace art
#endif //HOTFIX_ART_H