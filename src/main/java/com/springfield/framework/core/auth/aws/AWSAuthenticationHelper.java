package com.springfield.framework.core.auth.aws;

import com.springfield.framework.core.exception.CognitoInternalErrorException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


/**
 * Helper class for calculating SRP client side maths
 * Reference - https://github.com/aws-samples/aws-cognito-java-desktop-app/blob/master/src/main/java/com/amazonaws/sample/cognitoui/AuthenticationHelper.java
 */
public class AWSAuthenticationHelper {
    private BigInteger randomBigInteger;
    private BigInteger bigInteger;
    private String poolName;

    public AWSAuthenticationHelper(String userPoolName) {
        do {
            randomBigInteger = new BigInteger(EPHEMERAL_KEY_LENGTH, SECURE_RANDOM).mod(newBigInteger);
            bigInteger = bigIntegerWithGivenPower.modPow(randomBigInteger, newBigInteger);
        } while (bigInteger.mod(newBigInteger).equals(BigInteger.ZERO));

        if (userPoolName.contains("_")) {
            poolName = userPoolName.split("_", 2)[1];
        } else {
            poolName = userPoolName;
        }
    }

    public BigInteger geta() {
        return randomBigInteger;
    }

    public BigInteger getRandomBigInteger() {
        return bigInteger;
    }


    // Seems to be 3072-bit Group from rfc5054
    private static final String HEX_N =
            "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD1"
                    + "29024E088A67CC74020BBEA63B139B22514A08798E3404DD"
                    + "EF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245"
                    + "E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7ED"
                    + "EE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3D"
                    + "C2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F"
                    + "83655D23DCA3AD961C62F356208552BB9ED529077096966D"
                    + "670C354E4ABC9804F1746C08CA18217C32905E462E36CE3B"
                    + "E39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9"
                    + "DE2BCBF6955817183995497CEA956AE515D2261898FA0510"
                    + "15728E5A8AAAC42DAD33170D04507A33A85521ABDF1CBA64"
                    + "ECFB850458DBEF0A8AEA71575D060C7DB3970F85A6E1E4C7"
                    + "ABF5AE8CDB0933D71E8C94E04A25619DCEE3D2261AD2EE6B"
                    + "F12FFA06D98A0864D87602733EC86A64521F2B18177B200C"
                    + "BBE117577A615D6C770988C0BAD946E208E24FA074E5AB31"
                    + "43DB5BFCE0FD108E4B82D120A93AD2CAFFFFFFFFFFFFFFFF";

    private static final BigInteger newBigInteger = new BigInteger(HEX_N, 16);
    private static final BigInteger bigIntegerWithGivenPower = BigInteger.valueOf(2);
    private static final BigInteger magnitudeBigInteger;

    private static final int EPHEMERAL_KEY_LENGTH = 1024;

    private static final ThreadLocal<MessageDigest> THREAD_MESSAGE_DIGEST =
            new ThreadLocal<MessageDigest>() {
                @Override
                protected MessageDigest initialValue() {
                    try {
                        return MessageDigest.getInstance("SHA-256");
                    } catch (NoSuchAlgorithmException e) {
                        throw new CognitoInternalErrorException("Exception in authentication", e);
                    }
                }
            };

    private static final SecureRandom SECURE_RANDOM;

    static {
        try {
            SECURE_RANDOM = SecureRandom.getInstance("SHA1PRNG");

            MessageDigest messageDigest = THREAD_MESSAGE_DIGEST.get();
            messageDigest.reset();
            messageDigest.update(newBigInteger.toByteArray());
            byte[] digest = messageDigest.digest(bigIntegerWithGivenPower.toByteArray());
            magnitudeBigInteger = new BigInteger(1, digest);
        } catch (NoSuchAlgorithmException e) {
            throw new CognitoInternalErrorException(e.getMessage(), e);
        }
    }
}