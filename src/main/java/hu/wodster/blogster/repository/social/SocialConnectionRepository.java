package hu.wodster.blogster.repository.social;

import hu.wodster.blogster.model.social.SocialConnection;
import hu.wodster.blogster.model.social.SocialNetwork;
import hu.wodster.blogster.model.user.User;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface to manage {@link SocialConnection} entities.
 *
 * @author Károly
 *
 */
public interface SocialConnectionRepository extends
		CrudRepository<SocialConnection, Long> {

	public SocialConnection findTopByProviderUserIdOrderByRankAsc(
			final String providerUserId);

	public SocialConnection findByUserAndNetworkAndProviderUserId(
			final User user, final SocialNetwork network,
			final String providerUserId);

	public List<SocialConnection> findByNetworkAndProviderUserId(
			final SocialNetwork network, final String providerUserId);

	public SocialConnection findTopByUserAndNetworkOrderByRankAsc(
			final User user, final SocialNetwork network);

	public List<SocialConnection> findByUserAndNetworkOrderByRankAsc(
			final User user, final SocialNetwork network);

	public List<SocialConnection> findByUserOrderByNetworkAscRankAsc(
			final User user);

	public void deleteByNetwork(final SocialNetwork network);

	public SocialConnection deleteByUserAndNetworkAndProviderUserId(
			final User user, final SocialNetwork network,
			final String providerUserId);

	public List<SocialConnection> findByNetworkAndProviderUserIdIn(
			final SocialNetwork network, final Set<String> providerUserIds);
}
