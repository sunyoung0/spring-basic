package com.sun.basic.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sun.basic.entity.UserEntity;
import com.sun.basic.repository.UserRepository;
import com.sun.basic.service.MainService;

import lombok.RequiredArgsConstructor;

// description : Service - 레이어드 아키텍처 상의 비즈니스 영역 //
// description : 실제 비즈니스 로직(연산, 유효성 검사 등)이 작성되는 구역 //

// description : @Service - 해당 클래스가 Service 영역임을 명시 //
// description : @Component를 포함하고 있어서 Spring Bean으로 등록할 수 있음 //
// description : Spring Bean으로 등록해야 Spring에게 IoC 할 수 있음 //
@Service
@RequiredArgsConstructor
public class MainServiceImplement implements MainService {

	private final UserRepository userRepository;

	@Override
	public String hello() {
		UserEntity userEntity = new UserEntity("email", "12341234", "zero", "01012341234", "서울시", "부산진구", null);
		userRepository.save(userEntity);
		return "hello World";
	}

	@Override
	public String getRepository() {
		List<UserEntity> userEntity = userRepository.findByNicknameOrAddress("zero", "서울시");
		return userEntity.toString();
	}
}
