package com.wipro.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wipro.entity.User;
@DataJpaTest
public class UserRepositoryTests{
	
	@Autowired
	private UserRepository repository;

	@Test

	@DisplayName("saving the user Details")
	public void saveUsers() {
		User user = User.builder().email("abc12@gmail.com").password("pass").role("USER").build();
		User saveUser = repository.save(user);
		assertThat(saveUser).isNotNull();

	}

//	@Test
//	@DisplayName("getting the User Details")
//	public void getUserDetails() {
//		User u1 =  User.builder().id(5).email("sunil@gmail.com").password("sunil").role("USER").build();
//		User u2 =  User.builder().id(6).email("harish@gmail.com").password("harish").role("USER").build();
//		 repository.save(u1);
//		 repository.save(u2);
//		 List<User> user =(List<User>) repository.findAll();
//		 assertThat(user.size()).isEqualTo(2);
//	}
	

	@Test
	@DisplayName("Getting the user details by using id")
	public void getProjectById() {
		User user = User.builder().email("harish@gmail.com").password("harish").role("USER").build();
		User saveuser = repository.save(user);
		User prj = repository.findById(saveuser.getId()).get();
		assertThat(prj).isNotNull();
		assertThat(prj.getId()).isGreaterThan(0);
	}
	@Test
	@DisplayName("updating the details by using  id")
	public void updateUser() {
		User user = User.builder().email("naidu@gmail.com").password("naidu").role("USER").build();
		User saveuser = repository.save(user);
		User pro = repository.findById(saveuser.getId()).get();
		pro.setEmail("sankar123@gmail.com");
		pro.setPassword("no1");
		pro.setRole("USER");
		User proj = repository.save(pro);
		assertThat(proj.getEmail()).isEqualTo("sankar123@gmail.com");
		assertThat(proj.getPassword()).isEqualTo("no1");
		assertThat(proj.getRole()).isEqualTo("USER");
		
	}
	@Test
	@DisplayName("deleting the details by using  id")
	public void deleteProject() {
		User user = User.builder().email("naidu@gmail.com").password("naidu").role("USER").build();
		User saveuser = repository.save(user);
		repository.delete(saveuser);
		Optional<User> op = repository.findById(saveuser.getId());
		assertThat(op).isEmpty();
	}
}