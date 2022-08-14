package com.y2829.whai.oauth.info;

import com.y2829.whai.oauth.entity.ProviderType;
import com.y2829.whai.oauth.exception.OAuthProviderMissMatchException;
import com.y2829.whai.oauth.info.impl.*;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        return switch (providerType) {
            case GITHUB -> new GithubOAuth2UserInfo(attributes);
            case KAKAO -> new KakaoOAuth2UserInfo(attributes);
            case NAVER -> new NaverOAuth2UserInfo(attributes);
            case GOOGLE -> new GoogleOAuth2UserInfo(attributes);
            case FACEBOOK -> new FacebookOAuth2UserInfo(attributes);
            default -> throw new OAuthProviderMissMatchException("miss match provider");
        };
    }

}
