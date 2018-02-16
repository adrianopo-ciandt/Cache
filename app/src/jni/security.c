#include <jni.h>

jstring Java_br_com_app_cache_infrastructure_SecurityBridge_getKeys( JNIEnv* env, jobject thiz, jobject context) {

    jstring VALUE = "OUUohdajc94VqFobFS3Fyp5K8JPVPF5/jexLJYRApqFSvYVfTff5+I+HbyiiJkjDsSFCXVmdu6BesgtUFwvMOA==";

// MARK TO GRADLE - DON'T REMOVE IT!

    jclass arraysClass = (*env)->FindClass(env, "java/util/Arrays");
    jclass base64Class = (*env)->FindClass(env, "android/util/Base64");
    jclass cipherClass = (*env)->FindClass(env, "javax/crypto/Cipher");
    jclass integerClass = (*env)->FindClass(env, "java/lang/Integer");
    jclass ivParameterSpecClass = (*env)->FindClass(env, "javax/crypto/spec/IvParameterSpec");
    jclass messageDigestClass = (*env)->FindClass(env, "java/security/MessageDigest");
    jclass packageInfoClass = (*env)->FindClass(env, "android/content/pm/PackageInfo");
    jclass packageManagerClass = (*env)->FindClass(env, "android/content/pm/PackageManager");
    jclass secretKeySpecClass = (*env)->FindClass(env, "javax/crypto/spec/SecretKeySpec");
    jclass stringClass = (*env)->FindClass(env, "java/lang/String");
    jclass stringBuilderClass = (*env)->FindClass(env, "java/lang/StringBuilder");

    jclass contextClass = (*env)->GetObjectClass(env, context);

    jmethodID getPackageNameMethodID = (*env)->GetMethodID(env, contextClass, "getPackageName", "()Ljava/lang/String;");
    jstring packageName =  (jstring)(*env)->CallObjectMethod(env, context, getPackageNameMethodID);

    jmethodID getPackageManager =  (*env)->GetMethodID(env, contextClass, "getPackageManager", "()Landroid/content/pm/PackageManager;");
    jobject packageManagerObject = (*env)->CallObjectMethod(env, context, getPackageManager);

    jfieldID versionCodeFieldID = (*env)->GetFieldID(env, packageInfoClass, "versionCode", "I");
    jfieldID versionNameFieldID = (*env)->GetFieldID(env, packageInfoClass, "versionName", "Ljava/lang/String;");

    jmethodID getPackageInfo = (*env)->GetMethodID(env, packageManagerClass, "getPackageInfo", "(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");
    jobject packageInfoObject = (*env)->CallObjectMethod(env, packageManagerObject, getPackageInfo, packageName, 0x0);

    int versionCode = (*env)->GetIntField(env, packageInfoObject, versionCodeFieldID);
    jstring versionName = (jstring) (*env)->GetObjectField(env, packageInfoObject, versionNameFieldID);

    jmethodID versionCodeIntegerBuilderID = (*env)->GetMethodID(env, integerClass, "<init>", "(I)V");
    jobject versionCodeIntegerBuilderObj = (*env)->NewObject(env, integerClass, versionCodeIntegerBuilderID, versionCode);
    jmethodID versionCodeIntegerMethodID = (*env)->GetMethodID(env, integerClass, "toString", "()Ljava/lang/String;");
    jstring versionCodeString  = (*env)->CallObjectMethod(env, versionCodeIntegerBuilderObj, versionCodeIntegerMethodID);

    jmethodID initStringBuilderID = (*env)->GetMethodID(env, stringBuilderClass, "<init>", "(Ljava/lang/String;)V");
    jobject stringBuilderObj = (*env)->NewObject(env, stringBuilderClass, initStringBuilderID, packageName);
    jmethodID stringBuilderAppendID = (*env)->GetMethodID(env, stringBuilderClass, "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
    stringBuilderObj = (*env)->CallObjectMethod(env, stringBuilderObj, stringBuilderAppendID, (*env)->NewStringUTF(env, ":"));
    stringBuilderObj = (*env)->CallObjectMethod(env, stringBuilderObj, stringBuilderAppendID, versionCodeString);
    stringBuilderObj = (*env)->CallObjectMethod(env, stringBuilderObj, stringBuilderAppendID, (*env)->NewStringUTF(env, ":"));
    stringBuilderObj = (*env)->CallObjectMethod(env, stringBuilderObj, stringBuilderAppendID, versionName);

    jmethodID stringBuilderToStringID = (*env)->GetMethodID(env, stringBuilderClass, "toString", "()Ljava/lang/String;");
    jstring aesKey = (*env)->CallObjectMethod(env, stringBuilderObj, stringBuilderToStringID);

    jmethodID initStringID = (*env)->GetMethodID(env, stringClass, "<init>", "(Ljava/lang/String;)V");
    jobject keyStringObj = (*env)->NewObject(env, stringClass, initStringID, aesKey);
    jmethodID keyBytesID = (*env)->GetMethodID(env, stringClass, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray bytesKey = (*env)->CallObjectMethod(env, keyStringObj, keyBytesID, (*env)->NewStringUTF(env, "UTF-8"));

    jmethodID instanceMessageDigestID = (*env)->GetStaticMethodID(env, messageDigestClass, "getInstance", "(Ljava/lang/String;)Ljava/security/MessageDigest;");
    jstring sha1 = (*env)->NewStringUTF(env, "SHA-1");
    jobject messageDigestObj = (*env)->CallStaticObjectMethod(env, messageDigestClass, instanceMessageDigestID, sha1);

    jclass messageDigestObjectClass = (*env)->GetObjectClass(env, messageDigestObj);

    jmethodID digestMessageDigestMethodID = (*env)->GetMethodID(env, messageDigestObjectClass, "digest", "([B)[B");
    bytesKey = (*env)->CallObjectMethod(env, messageDigestObj, digestMessageDigestMethodID, bytesKey);

    jmethodID copyOfArraysStaticMethodID = (*env)->GetStaticMethodID(env, arraysClass, "copyOf", "([BI)[B");
    bytesKey = (*env)->CallStaticObjectMethod(env, arraysClass, copyOfArraysStaticMethodID, bytesKey, 16);

    jmethodID initSecretKeySpecID = (*env)->GetMethodID(env, secretKeySpecClass, "<init>", "([BLjava/lang/String;)V");
    jstring aes = (*env)->NewStringUTF(env, "AES");
    jobject secretKeyObj = (*env)->NewObject(env, secretKeySpecClass, initSecretKeySpecID, bytesKey, aes);

    jmethodID instanceCipherID = (*env)->GetStaticMethodID(env, cipherClass, "getInstance", "(Ljava/lang/String;)Ljavax/crypto/Cipher;");
    jstring aesCipher = (*env)->NewStringUTF(env, "AES/CBC/PKCS5Padding");
    jobject cipherObj = (*env)->CallStaticObjectMethod(env, cipherClass, instanceCipherID, aesCipher);

    jmethodID initStringRandomID = (*env)->GetMethodID(env, stringClass, "<init>", "(Ljava/lang/String;)V");
    jobject keyStringRandomObj = (*env)->NewObject(env, stringClass, initStringRandomID, (*env)->NewStringUTF(env, "RandomInitVector"));
    jmethodID keyBytesRandomID = (*env)->GetMethodID(env, stringClass, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray bytesRandomKey = (*env)->CallObjectMethod(env, keyStringRandomObj, keyBytesRandomID, (*env)->NewStringUTF(env, "UTF-8"));

    jmethodID initParameterSpecMethodID = (*env)->GetMethodID(env, ivParameterSpecClass, "<init>", "([B)V");
    jobject ivParameterSpecObj = (*env)->NewObject(env, ivParameterSpecClass, initParameterSpecMethodID, bytesRandomKey);

    jclass cipherObjectClass = (*env)->GetObjectClass(env, cipherObj);

    jmethodID initCipherID = (*env)->GetMethodID(env, cipherObjectClass, "init", "(ILjava/security/Key;Ljava/security/spec/AlgorithmParameterSpec;)V");
    (*env)->CallVoidMethod(env, cipherObj, initCipherID, 2, secretKeyObj, ivParameterSpecObj);

    jmethodID decodeMethodID = (*env)->GetStaticMethodID(env, base64Class, "decode", "(Ljava/lang/String;I)[B");
    jbyteArray bytesValueDecrypt = (*env)->CallStaticObjectMethod(env, base64Class, decodeMethodID, (*env)->NewStringUTF(env, VALUE), 0);

    jmethodID doFinalCipherID = (*env)->GetMethodID(env, cipherObjectClass, "doFinal", "([B)[B");
    jbyteArray bytesResult = (*env)->CallObjectMethod(env, cipherObj, doFinalCipherID, bytesValueDecrypt);

    jmethodID ctorID = (*env)->GetMethodID(env, stringClass, "<init>", "([BLjava/lang/String;)V");
    jstring encoding = (*env)->NewStringUTF(env, "GBK");

    jstring str = (jstring) (*env)->NewObject(env, stringClass, ctorID, bytesResult, encoding);

    return str;
}
