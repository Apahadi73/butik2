import { FontAwesome } from "@expo/vector-icons";
import React from "react";
/**
 * You can explore the built-in icon families and icons on the web at https://icons.expo.fyi/
 */
export default function TabBarIcon(props: {
  name: React.ComponentProps<typeof FontAwesome>["name"];
  color: string;
}) {
  return <FontAwesome size={30} style={{ marginBottom: -3 }} {...props} />;
}
