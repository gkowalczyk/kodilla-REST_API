package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/*Aplikacja  "TASKS" -  odczyt  zadań z bazy danych o konkretnej encji ID

1. Klasa DbServive odpowiada za komunikację z bazą danych "tasks_crud".
Klasa ta wstrzykuje do siebie klasę TaskRepository (zawiera interfejs rozrzerzony o
CrudRepository - znajdujący się w bibliotekach sprongframework) przy pomocy adnotacji @Autowired

2. Klasa TaskMapper mapuje typ Task na TaskDto oraz odwrotnie

3. W klasie TaskController dokonujemy wstrzyknięcia klas DbService oraz TaskMapper

4. Przy użyciu Postman-a wysyłyamy żądania HTTP do controllera aplikacji, używamy metod :
 - GET ( POBIERANIE DANYCH Z SERWERA)
 - POST (WSTAWIANIE DANYCH NA SERWER)
 - DELETE (USUWANIE DANYCH Z SERWERA)
 - PUT (UPDATE DANYCH NA SERWERZE)
 */

//Obiekty domenowe są nieodłączną częścią aplikacji.
// Są obiektami, które przenoszą informacje wewnątrz
// aplikacji oraz pełnią funkcję deskrypcyjną, czyli
// opisują, na danych jakiego rodzaju operujemy w
// danym momencie w kodzie. Nazwy obiektów
// domenowych powinny spełniać założenia czystego kodu,
// dlatego należy wyzbyć się końcówek typu
// -Data, czy -Information.
//
//DTO również są częścią obiektów domenowych.
@SpringBootApplication

//public class TasksApplication extends SpringBootServletInitializer {
public class TasksApplication {


	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class,args);
		//TaskDto taskDto = new TaskDto(
			//	(long)1,
				//"Test title",
				//"I want to be a coder"

		//);
		//Long id = taskDto.getId();
		//String title = taskDto.getTitle();
		//String content = taskDto.getContent();
		//System.out.println(id + " " + title + " " + content);

	}
	//@Override
//protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
	//	return application.sources(TasksApplication.class);
	//}
}
