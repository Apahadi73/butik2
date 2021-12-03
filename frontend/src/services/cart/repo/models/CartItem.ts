import { ProductModel } from "../../../products/repo/models/ProductModel";

export interface CartItemModel extends ProductModel {
  number: number;
}
