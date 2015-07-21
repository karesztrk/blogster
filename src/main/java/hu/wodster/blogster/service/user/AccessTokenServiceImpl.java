package hu.wodster.blogster.service.user;

import hu.wodster.blogster.repository.user.UserRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessTokenServiceImpl implements AccessTokenService {

	/**
	 * User repository.
	 */
	@Autowired
	private UserRepository userRepository;

	@Override
	public OAuth2Authentication readAuthentication(final OAuth2AccessToken token) {
		// TODO Auto-generated method stub
		System.out.println("readAuthentication");
		return null;
	}

	@Override
	public OAuth2Authentication readAuthentication(final String token) {
		// TODO Auto-generated method stub
		System.out.println("readAuthentication");
		return null;
	}

	@Override
	public void storeAccessToken(final OAuth2AccessToken token,
			final OAuth2Authentication authentication) {
		// TODO Auto-generated method stub
		System.out.println("storeAccessToken");
	}

	@Override
	public OAuth2AccessToken readAccessToken(final String tokenValue) {
		// TODO Auto-generated method stub
		System.out.println("readAccessToken");
		return null;
	}

	@Override
	public void removeAccessToken(final OAuth2AccessToken token) {
		// TODO Auto-generated method stub
		System.out.println("removeAccessToken");
	}

	@Override
	public void storeRefreshToken(final OAuth2RefreshToken refreshToken,
			final OAuth2Authentication authentication) {
		// TODO Auto-generated method stub
		System.out.println("storeRefreshToken");
	}

	@Override
	public OAuth2RefreshToken readRefreshToken(final String tokenValue) {
		// TODO Auto-generated method stub
		System.out.println("readRefreshToken");
		return null;
	}

	@Override
	public OAuth2Authentication readAuthenticationForRefreshToken(
			final OAuth2RefreshToken token) {
		// TODO Auto-generated method stub
		System.out.println("readAuthenticationForRefreshToken");
		return null;
	}

	@Override
	public void removeRefreshToken(final OAuth2RefreshToken token) {
		// TODO Auto-generated method stub
		System.out.println("removeRefreshToken");
	}

	@Override
	public void removeAccessTokenUsingRefreshToken(
			final OAuth2RefreshToken refreshToken) {
		// TODO Auto-generated method stub
		System.out.println("removeAccessTokenUsingRefreshToken");
	}

	@Override
	public OAuth2AccessToken getAccessToken(
			final OAuth2Authentication authentication) {
		// TODO Auto-generated method stub
		System.out.println("getAccessToken");
		return null;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(
			final String clientId, final String userName) {
		// TODO Auto-generated method stub
		System.out.println("findTokensByClientIdAndUserName");
		return null;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientId(
			final String clientId) {
		// TODO Auto-generated method stub
		System.out.println("findTokensByClientId");
		return null;
	}

}
