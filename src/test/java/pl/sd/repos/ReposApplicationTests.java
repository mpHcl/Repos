package pl.sd.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;
import pl.sd.repos.client.GitHubClient;
import pl.sd.repos.model.RepoModel;
import pl.sd.repos.service.ReposService;

import java.util.Collections;
import java.util.List;

class ReposServiceTest {

	private GitHubClient gitHubClient;
	private ReposService reposService;

	@BeforeEach
	void setUp() {
		gitHubClient = mock(GitHubClient.class);
		reposService = new ReposService(gitHubClient);
	}

	@Test
	void getUserRepositoriesInfo_Success() {
		// Arrange
		String username = "testUser";
		List<RepoModel> expectedRepositories = Collections.singletonList(
				RepoModel.builder()
						.name("testRepo")
						.ownerLogin("testUser")
						.build()
		);
		when(gitHubClient.getUserRepositoriesInfo(username)).thenReturn(expectedRepositories);

		// Act
		List<RepoModel> actualRepositories = reposService.getUserRepositoriesInfo(username);

		// Assert
		assertEquals(expectedRepositories, actualRepositories);
	}

	@Test
	void getUserRepositoriesInfo_ClientErrorException() {
		// Arrange
		String username = "nonExistingUser";
		when(gitHubClient.getUserRepositoriesInfo(username)).thenThrow(HttpClientErrorException.class);

		// Act & Assert
		assertThrows(HttpClientErrorException.class, () -> reposService.getUserRepositoriesInfo(username));
	}
}