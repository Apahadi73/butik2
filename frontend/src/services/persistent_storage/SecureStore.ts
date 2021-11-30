import * as SecureStore from "expo-secure-store";

export const PSKeyEnum = {
  TOKEN: "token",
  EMAIL: "email",
};

const PersistentStorage = {
  // save key-value to the secure store
  save: async (key: string, value: any) => {
    await SecureStore.setItemAsync(key, value);
  },

  // extract value from secure store using key
  fetchValue: async function getValueFor(key: string) {
    let result = await SecureStore.getItemAsync(key);
    if (result) {
      return result;
    } else {
      console.log("No values stored under that key.");
      return null;
    }
  },

  deleteKey: async (key: string) => {
    let result = await SecureStore.deleteItemAsync(key);
  },
};

export default PersistentStorage;
