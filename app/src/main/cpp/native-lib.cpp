#include <jni.h>
#include <string>
#include "art.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_yuan_hotfix_andfix_DxManager_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT void JNICALL
Java_com_yuan_hotfix_andfix_DxManager_replaceJNI(JNIEnv *env, jobject instance, jobject wrongMethod,
                                                 jobject rightMethod) {
    art::ArtMethod *artReplaceMethod = (art::ArtMethod *) env->FromReflectedMethod(
            wrongMethod);
    art::ArtMethod *artMethod = (art::ArtMethod *) env->FromReflectedMethod(
            rightMethod);
    artReplaceMethod->access_flags_ = artMethod->access_flags_;
    artReplaceMethod->declaring_class_ = artMethod->declaring_class_;
    artReplaceMethod->dex_code_item_offset_ = artMethod->dex_code_item_offset_;
    artReplaceMethod->dex_method_index_ = artMethod->dex_method_index_;
    artReplaceMethod->hotness_count_ = artMethod->hotness_count_;
    artReplaceMethod->method_index_ = artMethod->method_index_;
    artReplaceMethod->ptr_sized_fields_.dex_cache_resolved_methods_ = artMethod->ptr_sized_fields_.dex_cache_resolved_methods_;
    artReplaceMethod->ptr_sized_fields_.dex_cache_resolved_types_ = artMethod->ptr_sized_fields_.dex_cache_resolved_types_;
    artReplaceMethod->ptr_sized_fields_.entry_point_from_jni_ = artMethod->ptr_sized_fields_.entry_point_from_jni_;
    artReplaceMethod->ptr_sized_fields_.entry_point_from_quick_compiled_code_ = artMethod->ptr_sized_fields_.entry_point_from_quick_compiled_code_;
    printf("com.yuan.hotfix 555");
}
