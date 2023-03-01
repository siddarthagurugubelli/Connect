import React, { useEffect, useRef, useContext } from "react";
import { Avatar, Flex, Text } from "@chakra-ui/react";
import ChatService from "../services/ChatService";
import { useState } from "react";
import AuthContext from "../context/AuthContext";

const Messages = ({messages, setMessages}) => {
  const AlwaysScrollToBottom = () => {
    const elementRef = useRef();
    useEffect(() => elementRef.current.scrollIntoView());
    return <div ref={elementRef} />;
  };

  const chatService = new ChatService()

  const { user } = useContext(AuthContext)

  return (
    <Flex w="100%" h="80%" overflowY="scroll" flexDirection="column" p="3">
      {messages.map((item, index) => {
        if (item.senderId === user.id) {
          return (
            <Flex key={index} w="100%" alignItems="flex-end" direction={"column"}>
              <Text fontSize={"12px"} mt={"5px"}>{new Date(item.createdAt).toLocaleDateString()} {new Date(item.createdAt).toLocaleTimeString('en-US')}</Text>
              <Flex
                bg="darkseagreen"
                color="white"
                minW="100px"
                maxW="350px"
                my="1"
                p="3"
              >
                <Text wordBreak={"break-all"}>{item.message}</Text>
              </Flex>
            </Flex>
          );
        } else {
          return (
            <Flex key={index} w="100%" direction={"column"}>
              {/* <Avatar
                name={item.email}
                // src="https://avataaars.io/?avatarStyle=Transparent&topType=LongHairStraight&accessoriesType=Blank&hairColor=BrownDark&facialHairType=Blank&clotheType=BlazerShirt&eyeType=Default&eyebrowType=Default&mouthType=Default&skinColor=Light"
                bg="blue.300"
              ></Avatar> */}
              <Flex gap={"30px"}>
              <Text fontWeight={800}>{item.email.split("@")[0]}</Text>
              <Text fontSize={"12px"} mt={"5px"}>{new Date(item.createdAt).toLocaleDateString()} {new Date(item.createdAt).toLocaleTimeString('en-US')}</Text>
              </Flex>
              <Flex
                bg="gray.100"
                color="black"
                minW="100px"
                maxW="350px"
                my="1"
                p="3"
              >
                <Text wordBreak={"break-all"}>{item.message}</Text>
              </Flex>
            </Flex>
          );
        }
      })}
      <AlwaysScrollToBottom />
    </Flex>
  );
};

export default Messages;