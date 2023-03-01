import { Button, Flex, Input, Table, Tbody, Td, Text, Thead, Tr, VStack } from "@chakra-ui/react";
import { useContext, useEffect, useState } from "react";
import Nav from "../components/Nav";
import AuthContext from "../context/AuthContext";
import FollowService from "../services/FollowService";
import UserService from "../services/UserService";


export default function Accounts(){
    const [allUsers, setAllUsers] = useState([])
    const { user } = useContext(AuthContext)

    const userService = new UserService();
    const followService = new FollowService()
    useEffect(() => {
        userService.getAllUsers(localStorage.getItem("token")).then((res) => setAllUsers(res.data))
    },[])

    const handleFollow = async(followerId, type) => {
        const obj = {
           userId: user.id,
           followingId: followerId
        }
        if(type === "follow"){
            await followService.follow(obj, localStorage.getItem("token"))
        }
        else{
            await followService.unfollow(obj, localStorage.getItem("token"))
        }
        await userService.getAllUsers(localStorage.getItem("token")).then((res) => setAllUsers(res.data))

    }

    const checkIsFollowing = (followers) => {
        return followers?.find(item => item?.userId === user.id)
    }

    return(
        <>
        <Nav />
                <VStack marginTop={'50px'} marginLeft={'200px'}>
                    <Flex w="100%" h="100vh" direction={"column"}>
                        <Text fontSize={30} fontWeight={800}>Accounts</Text>
                        <Table w={"70%"}>
                            <Thead>
                                <Tr> 
                                    <Td fontWeight={600}>
                                        Email
                                    </Td>
                                </Tr>
                            </Thead>
                            <Tbody>
                                {allUsers?.map(item => 
                                    <Tr>
                                    <Td>
                                        <Flex justify={"space-between"}>
                                            <Text paddingTop={"6px"}>{item?.email}</Text>
                                            <Button 
                                                colorScheme={checkIsFollowing(item?.followers) !== undefined ? "cyan" : "blue"}
                                                color={'white'}
                                                rounded={'md'}
                                                onClick={() => checkIsFollowing(item?.followers) !== undefined ? handleFollow(item?.id, "unfollow") : handleFollow(item?.id, "follow")}
                                            >
                                                {checkIsFollowing(item?.followers) !== undefined ? "Following" : "Follow"}
                                            </Button>
                                        </Flex>
                                        
                                    </Td>
                                    </Tr>
                                )} 
                            </Tbody>
                        </Table>
                    </Flex>
                </VStack>
        </>
    )
}