package com.springfield.framework.core.auth.aws;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import static com.amazonaws.regions.Regions.US_EAST_1;

public class AWSClientBuilder {

    /**
     * This method uses AWS.CognitoIdentityCredentials to authenticate given user.
     * Note: The given user should already be created within the AWS User Identity Pool with identity providers attached.
     */
    public static AWSCognitoIdentityProvider getAmazonCognitoIdentityClient() {
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withRegion(US_EAST_1)
                .build();
    }

    /**
     * This method uses creates an AmazonS3Client.
     * Note: The given user should already be created within the AWS User Identity Pool with identity providers attached.
     */
    public static AmazonS3 getAmazonS3Client() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(US_EAST_1)
                .build();
    }
}
