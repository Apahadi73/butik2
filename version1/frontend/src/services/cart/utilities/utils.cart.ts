import { CartConstants } from "./CONSTANTS";

export const caculateTax = (total: number) => {
  return CartConstants.TAX_RATE * total;
};

export const caculateOrderTotal = (total: number) => {
  return CartConstants.TAX_RATE * total + total;
};
