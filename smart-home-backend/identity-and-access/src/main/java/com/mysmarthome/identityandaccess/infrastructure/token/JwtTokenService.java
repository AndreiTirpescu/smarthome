package com.mysmarthome.identityandaccess.infrastructure.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mysmarthome.identityandaccess.domain.aggregate.User;
import com.mysmarthome.identityandaccess.domain.infrastructure.ITokenGenerator;
import com.mysmarthome.identityandaccess.domain.infrastructure.ITokenValidator;
import com.mysmarthome.identityandaccess.domain.valueobjects.Token;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserDescriptor;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserId;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserRole;
import com.mysmarthome.identityandaccess.domain.valueobjects.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JwtTokenService implements ITokenGenerator, ITokenValidator {

    private final TokenConfiguration tokenConfiguration;

    @Override
    public Token generateTokenForUser(User user) {
        var tokenKeySign = new RSASecureTokenKey(tokenConfiguration.keyId, tokenConfiguration.privateKey, tokenConfiguration.publicKey);
        var keyPair = tokenKeySign.keyPair();

        var accessToken = JWT.create()
                .withIssuer(tokenConfiguration.issuer)
                .withIssuedAt(Instant.now())
                .withSubject(user.getId().toString())
                .withAudience("user")
                .withKeyId(tokenKeySign.id().toString())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(tokenConfiguration.durationInSeconds))
                .withClaim("email", user.getEmail().toString())
                .withClaim("status", user.getStatus().name())
                .withClaim("role", user.getRole().name());

        var refreshToken = accessToken.withExpiresAt(Instant.now().plusSeconds(tokenConfiguration.refreshDurationInSeconds));
        String signedAccessToken = accessToken.sign(Algorithm.RSA256((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate()));
        String signedRefreshToken = refreshToken.sign(Algorithm.RSA256((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate()));

        return new Token(signedAccessToken, signedRefreshToken);
    }

    @Override
    @SneakyThrows
    public UserDescriptor extractUserDescriptorFromToken(String accessToken) {
        var tokenKeySign = new RSASecureTokenKey(tokenConfiguration.keyId, tokenConfiguration.privateKey, tokenConfiguration.publicKey);
        var keyPair = tokenKeySign.keyPair();

        var decodedToken = JWT.require(Algorithm.RSA256((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate()))
                .build().verify(accessToken);

        return new UserDescriptor(
                new UserId(decodedToken.getSubject()),
                UserRole.valueOf(decodedToken.getClaim("role").asString()),
                UserStatus.valueOf(decodedToken.getClaim("status").asString())
        );
    }

    @Override
    public boolean isValid(String accessToken) {
        try {
            var tokenKeySign = new RSASecureTokenKey(tokenConfiguration.keyId, tokenConfiguration.privateKey, tokenConfiguration.publicKey);
            var keyPair = tokenKeySign.keyPair();

            JWT.require(Algorithm.RSA256((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate()))
                    .build().verify(accessToken);
        } catch (JWTVerificationException ex) {
            return false;
        }

        return true;
    }
}
