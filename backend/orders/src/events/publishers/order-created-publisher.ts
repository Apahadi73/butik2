import { Publisher, OrderCreatedEvent, Subjects } from "@mgktickets/common";
export class OrderCreatedPublisher extends Publisher<OrderCreatedEvent> {
  subject: Subjects.OrderCreated = Subjects.OrderCreated;
}
