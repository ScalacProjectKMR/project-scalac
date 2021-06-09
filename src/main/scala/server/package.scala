import zio.Has
import server.domain.services._

package object server {
  type ShopDatabase       = Has[ShopDatabase.Service]
  type ProductReadService = Has[ProductReadService.Service]
}
