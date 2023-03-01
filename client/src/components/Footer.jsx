import React, { useContext, useState } from "react";
import { Flex, Input, Button } from "@chakra-ui/react";
import UserService from "../services/UserService";
import AuthContext from "../context/AuthContext";
import ChatService from "../services/ChatService";

const Footer = ({inputMessage, setInputMessage, handleSendMessage}) => {

    
  return (
    <Flex w="100%" mt="5">
      <Input
        placeholder="Type Something..."
        border="none"
        borderRadius="none"
        _focus={{
          border: "1px solid black",
        }}
        // onKeyPress={(e) => {
        //   if (e.key === "Enter") {
        //     handleSendMessage();
        //   }
        // }}
        value={inputMessage}
        onChange={(e) => setInputMessage(e.target.value)}
      />
      <Button
        bg="black"
        color="white"
        borderRadius="none"
        _hover={{
          bg: "white",
          color: "black",
          border: "1px solid black",
        }}
        disabled={inputMessage.trim().length <= 0}
        onClick={handleSendMessage}
      >
        Send
      </Button>
    </Flex>
  );
};

export default Footer;
