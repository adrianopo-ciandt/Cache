LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE    := security
LOCAL_SRC_FILES := security.c
LOCAL_PRELINK_MODULE := false
include $(BUILD_SHARED_LIBRARY)