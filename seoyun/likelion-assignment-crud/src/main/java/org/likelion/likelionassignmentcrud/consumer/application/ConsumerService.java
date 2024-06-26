package org.likelion.likelionassignmentcrud.consumer.application;

import org.springframework.transaction.annotation.Transactional;
import org.likelion.likelionassignmentcrud.consumer.domain.Consumer;
import org.likelion.likelionassignmentcrud.consumer.domain.repository.ConsumerRepository;
import org.likelion.likelionassignmentcrud.consumer.api.dto.request.ConsumerSaveReqDto;
import org.likelion.likelionassignmentcrud.consumer.api.dto.response.ConsumerInfoResDto;
import org.likelion.likelionassignmentcrud.consumer.api.dto.response.ConsumerListResDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(readOnly =true)
public class ConsumerService {
    private final ConsumerRepository consumerRepository;

    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    // Create
    @Transactional
    public void consumerSave(ConsumerSaveReqDto consumerSaveReqDto) {
        Consumer consumer = Consumer.builder()
                .name(consumerSaveReqDto.name())
                .age(consumerSaveReqDto.age())
                .part(consumerSaveReqDto.part())
                .build();

        consumerRepository.save(consumer);
    }

    // Read All
    public ConsumerListResDto consumerFindAll() {
        List<Consumer> consumers = consumerRepository.findAll();

        List<ConsumerInfoResDto> consumerInfoResDtoList = consumers.stream()
                .map(ConsumerInfoResDto::from)
                .toList();

        return ConsumerListResDto.from(consumerInfoResDtoList);
    }

    // Read One
    public ConsumerInfoResDto consumerFindOne(Long memberId) {
        Consumer consumer = consumerRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);

        return ConsumerInfoResDto.from(consumer);
    }

}
