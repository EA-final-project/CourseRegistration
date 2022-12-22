package registrationsystem.kafkaSender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import registrationsystem.domain.Registration;
import registrationsystem.domain.StudentDetails;

@Service
public class KafkaSenderService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendStudentDetails(String topic, StudentDetails studentDetails) {

        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String studentAsString = objectMapper.writeValueAsString(studentDetails);
            kafkaTemplate.send(topic, studentAsString);
            System.out.println("Sending StudentDetails object");
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

    }



}
