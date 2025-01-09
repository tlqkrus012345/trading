package com.trading.transaction.api.event;

import com.trading.common.KafkaMessagePublisher;
import com.trading.common.KafkaTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ItemTransactionEventListener {

    private final KafkaMessagePublisher kafkaMessagePublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void publishCreatedOrder(ItemTransactionEvent event) {
        kafkaMessagePublisher.publish(
                KafkaTopic.ITEM_TRANSACTION_CREATED,
                event.getItemTransactionResponse().getItemTransactionId(),
                event.getItemTransactionResponse()
        );
    }
}
