package emailservice.kafkaReceiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import emailservice.domain.StudentDetails;
import emailservice.repository.StudentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver {
    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

    //properties = {"spring.json.value.default.type=com.something.model.TransactionEventPayload"}
    @KafkaListener(topics = {"student_details1"})
    public void receive(@Payload String studentDetails) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{

            Object student =  objectMapper.readValue(studentDetails,Object.class);
            StudentDetails kafkaStudent = objectMapper.readValue(studentDetails, new TypeReference<StudentDetails>(){});
            studentDetailsRepository.save(kafkaStudent);
            System.out.println("Receiving StudentDetails object " + student.toString());

        } catch (JsonProcessingException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
