import {
    Heading,
    Avatar,
    Box,
    Center,
    Image,
    Flex,
    Text,
    Stack,
    Button,
    useColorModeValue,
    useToast,
    Input,
    IconButton,
    useDisclosure,
} from '@chakra-ui/react';
import { useContext, useState } from 'react';
import AuthContext from '../context/AuthContext';
import FollowService from '../services/FollowService';
import UserService from '../services/UserService';
import ConfirmModal from './ConfirmModal';


function UserCard({ image, fullName, followers, following, isFollowing, isOwner, userId, checkIsFollowing }) {
    const { isOpen, onOpen, onClose } = useDisclosure()
    const [file, setFile] = useState(null)
    const handleImageChance = (e) => {
        setFile(e.target.files[0])
        e.target.files[0] && onOpen()
    }
    const imageUrl = process.env.REACT_APP_API + "userimages/download/" 

    const { user } = useContext(AuthContext)
    const followService = new FollowService()
    const toast = useToast()

    const handleFollow = async (userId, followingId) => {
        try {
            const values = { userId, followingId }
            console.log(values)
            const result = await followService.follow(values, localStorage.getItem("token"))
            checkIsFollowing()
            toast({
                title: "Followed",
                status: 'success',
                duration: 9000,
                isClosable: true,
            })
        } catch (error) {
            toast({
                title: "",
                status: 'error',
                duration: 9000,
                isClosable: true,
            })
            console.log(error)
        }
    }

    const handleUnFollow = async (userId, followingId) => {
        try {
            const values = { userId, followingId }
            await followService.unfollow(values, localStorage.getItem("token"))
            checkIsFollowing()
            toast({
                title: "Unfollowed",
                status: 'success',
                duration: 9000,
                isClosable: true,
            })
        } catch (error) {
            const values = { userId, followingId }
            console.log(values)
            toast({
                title: "",
                status: 'error',
                duration: 9000,
                isClosable: true,
            })

        }
    }
    const userService=  new UserService()
    const uploadPicture = ()=> {
        console.log(file)
        if(file !== null){
            const obj ={
                userId :user.id, 
                image : file
            }
            userService.profilePicUpload(obj, localStorage.getItem("token"))
        }
        onClose()
        window.location.reload()
    }

    return (
        <>
            <Flex position={{ base: 'relative', xl: 'fixed' }} alignItems={{ base: 'center', xl: 'flex-end' }} justifyContent={{ base: 'center', xl: 'flex-end' }} width={"100%"} right={{ xl: 12 }} top={{ base: 4, xl: 12 }}>
                <Center>
                    <Box
                        maxW={'270px'}
                        w={'xl'}
                        bg={useColorModeValue('white', 'gray.800')}
                        boxShadow={'2xl'}
                        rounded={'md'}
                        overflow={'hidden'}>
                        <Image
                            h={'120px'}
                            w={'full'}
                            src={
                                imageUrl + user.id 
                            }
                            fallbackSrc={"https://images.unsplash.com/photo-1612865547334-09cb8cb455da?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80"}
                            objectFit={'cover'}
                        />
                        <Flex justify={'center'} mt={-12}>
                            
                            <Button  as={'label'}>
                                <Image 
                                    src='/cameraIcon.png'
                                />
                                <input hidden type={'file'}
                                    accept="image/*" onChange={handleImageChance} placeholder='Last name' />
                            </Button>
                             
                        </Flex>

                        <Box p={6}>
                            <Stack spacing={0} align={'center'} mb={5}>
                                <Heading fontSize={'2xl'} fontWeight={500} fontFamily={'body'}>
                                    {fullName}
                                </Heading>
                            </Stack>

                            <Stack direction={'row'} justify={'center'} spacing={6}>
                                <Stack spacing={0} align={'center'}>
                                    <Text fontWeight={600}>{followers}</Text>
                                    <Text fontSize={'sm'} color={'gray.500'}>
                                        Followers
                                    </Text>
                                </Stack>
                                <Stack spacing={0} align={'center'}>
                                    <Text fontWeight={600}>{following}</Text>
                                    <Text fontSize={'sm'} color={'gray.500'}>
                                        Following
                                    </Text>
                                </Stack>
                            </Stack>
                            {/* {
                                isFollowing ?
                                    !isOwner &&
                                    <Button
                                        w={'full'}
                                        mt={8}
                                        colorScheme='blackAlpha'
                                        color={'white'}
                                        rounded={'md'}
                                        onClick={() => { handleUnFollow(user.id, parseInt(userId)) }}
                                    >
                                        Followed
                                    </Button>
                                    :
                                    !isOwner &&
                                    <Button
                                        w={'full'}
                                        mt={8}
                                        colorScheme='blue'
                                        color={'white'}
                                        rounded={'md'}
                                        onClick={() => { handleFollow(user.id, parseInt(userId)) }}
                                    >
                                        Follow
                                    </Button>
                            } */}


                        </Box>
                    </Box>
                </Center>

            </Flex>
            {
              isOpen &&
              <ConfirmModal
              open={isOpen}
              close={() => {
                window.location.reload();
                onClose()
              }}
              onSubmit={uploadPicture}
              header={" Do you want to upload picture?"}
              />
            }
        </>
    )
}

export default UserCard