package com.sun.basic.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.basic.dto.request.PostRequestBodyDto;
import com.sun.basic.provider.JwtProvider;
import com.sun.basic.service.MainService;

import lombok.RequiredArgsConstructor;

// description : Controller - 레이어드 아키텍처 상의 프레젠테이션 영역 //
// description : 사용자로부터 입력을 받고 서비스 결과를 반환하는 영역 //

// description : @RestController - REST API 형식의 Response를 반환하는 Controller임을 명시 //
// description : REST API 형식의 Response - Response 타입이 JSON 형태인 Response(응답) //
@RestController

// description : @RequestMapping - Request의 URL 패턴에 따라 클래스 및 메소드를 결정하는 어노테이션 //
// description : 예) http://localhost:8080/ //
@RequestMapping("")
@RequiredArgsConstructor	// 필수 멤버변수 생성자
public class MainController {

	// description : Spring Bean으로 등록된 인스턴스를 IoC(제어의 역전)로 DI(의존성 외부 주입) 하기 위해서는 //
	// description : @Autowired를 이용한 필드 객체로 DI(의존성 주입), 생성자로 DI, setter로 DI 방식을 사용해야함 //
	// description : Spring 공식 문서에서는 생성자를 이용한 의존성 주입을 권장 //
	// description : 생성자를 통한 의존성 주입은 @Autowired를 지정할 필요가 없음 //
	// description : 멤버 변수를 필수 변수(final)로 지정하여 lombok의 @RequiredArgsConstructor로 쉽게 DI 할 수 있음 //
	private final MainService mainService;

	private final JwtProvider jwtProvider;

	// description : @RequestMapping 중 Get method에 대해서만 인식 //
	@GetMapping("/")
	public String getMethod() {
		return mainService.hello();
	}

	// description : @RequestMapping 중 Post method에 대해서만 인식 //
	@PostMapping("/")
	public String postMethod() {
		return "post method";
	}

	// description : @RequestMapping 중 Patch method에 대해서만 인식 //
	@PatchMapping("/")
	public String patchMethod() {
		return "patch method";
	}

	// description : @RequestMapping 중 Put method에 대해서만 인식 //
	@PutMapping("/")
	public String putMethod() {
		return "put method";
	}

	// description : @RequestMapping 중 Delete method에 대해서만 인식 //
	@DeleteMapping("/")
	public String deleteMethod() {
		return "delete method";
	}

	// description : @PathVariable - path 자체를 변수의 값으로 인식 //
	// description : {variable} 로 표현 -> @PathVariable("variable") //
	@GetMapping("/path-valiable/{value}")
	public String getPathVariable(@PathVariable("value") String value) {
		return "입력한 path Valiable은 " + value + "입니다";
	}

	// description : @RequestParam - Query Parameter로 key와 value를 받아옴 //
	// description : 요청시 ?name1=value&name2=value2... //
	// description : @RequestParam("name1") //
	@GetMapping("/parameter")
	public String getParameter(@RequestParam("name") String name, @RequestParam("age") int age) {
		return "이름 : " + name + " 나이 : " + age;
	}

	// description : @RequestBody - Request Body에 포함된 데이터를 받아옴, 문자열 혹은 객체로 받을 수 있음 //
	// description : 요청 시 일반적으로 JSON형태로 RequestBody를 전송 //
	// description : @RequestBody //
	@PostMapping("/request-body")
	public String postRequestBody(
		// @RequestBody String requestBody
		@RequestBody @Valid PostRequestBodyDto requestBody
	)	{
		return "입력한 Request Body는 " + requestBody.getName() + "입니다.";
	}
	
	// description : Response Entity - Response의 상태 및 헤더, 본문을 직접 제어할 수 있는 클래스 //
	@GetMapping("/response-entity")
	public ResponseEntity<String> getResponseEntity() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Response Entity");
	}

	@GetMapping("/jwt/{sub}")
	public String getJwt(@PathVariable("sub") String sub) {
		String jwt = jwtProvider.create(sub);
		return jwt;
	}

	@PostMapping("/jwt")
	public String validateJwt(@RequestParam("jwt") String jwt) {
		String subject = jwtProvider.validate(jwt);
		return subject;
	}

	@PostMapping("/principle")
	public String principle (@AuthenticationPrincipal String subject){
		return "토큰에 포함된 subject는 " + subject + "입니다.";
	}

	@GetMapping("/encode-password/{password}")
	public String encodePassword(@PathVariable("password") String password) {
		String encodedPassword = mainService.getPasswordEncoding(password);
		return encodedPassword;
	}

	@PostMapping("/auth/match")
	public boolean isMatched(@RequestParam("password") String password, @RequestParam("encodedPassword") String encodedPassword){
		boolean isPasswordMatch = mainService.isPasswordMatch(password, encodedPassword);
		return isPasswordMatch;
	}
}