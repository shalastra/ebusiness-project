package models.cart

import scala.concurrent.Future

trait CartRepositoryTrait {
  def create(id: Long, orderId: Long): Future[Cart]
}
