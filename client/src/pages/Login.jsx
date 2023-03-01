import React, { useContext } from 'react'
import { Button, Container, Flex, FormControl, FormLabel, Heading, Image, Input, Stack, Text, useBreakpointValue, useToast, VStack } from '@chakra-ui/react'
import {useFormik} from 'formik'
import AuthContext from '../context/AuthContext'
import AuthService from '../services/AuthService'
import { Link, useNavigate } from 'react-router-dom'
import svg from '../svgs/main.svg'
import { useState } from 'react'


function Login() {

    const {login} = useContext(AuthContext)
    const authService = new AuthService()
    const navigate = useNavigate()
    const toast = useToast()
    const [loginFields , setLoginFields]= useState({
      email:"",
      password:""
    })
    const [loginFieldsErr, setLoginFieldsErr]= useState({
      emailErr:"",
      passwordErr:""
    })
    const isInputValid = (value, regex) => {
      const reg = value.match(regex);
      const isEmpty = value === ""
      return reg === null || isEmpty ? false : true
    }
    const handleInputChanges = {
      emailChange: (value) =>{
      setLoginFields({...loginFields,email:value})
      setLoginFieldsErr({...loginFieldsErr, emailErr: isInputValid(value, /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/) ? "" : "Invalid Email format"})
    },

     passwordChange: (value) =>{
      setLoginFields({...loginFields,password:value})
      setLoginFieldsErr({...loginFieldsErr,passwordErr: value !=="" ? "" : "Password cannot be empty"})
     }
    }
     const isErr= Object.keys(loginFieldsErr).some(key => loginFieldsErr[key] !== "")
    const handleSubmit= async() =>{
      if(!isErr){
        const obj = {
          email: loginFields.email,
          password: loginFields.password
        }
        await authService.login(obj).then((res) => {
          login(res.data)
        }).catch((err) => {
            toast({
              title: err?.response?.data || "Invalid details",
              status: 'error',
              duration: 1000,
              isClosable: true,
            })
        })
  
      }

    }
    



  return (
    <Stack direction={'row'} spacing={0} minH={'100vh'}>
      <Flex alignItems={'center'} justifyContent={'center'} width={{ base: 0, md: '100%', lg: '100%' }}>
        <VStack p={10} spacing={5}>
          <Stack spacing={6} w={'full'} maxW={'lg'}>
            <Heading fontSize={{ base: '0', md: '5xl', lg: '6xl' }}>
              <Text
                as={'span'}
                position={'relative'}
                _after={{
                  content: "''",
                  width: 'full',
                  height: useBreakpointValue({ base: '20%', md: '30%' }),
                  position: 'absolute',
                  bottom: 1,
                  left: 0,
                  bg: 'blue.500',
                  zIndex: -1,
                }}>
                CONNECT
              </Text>
              <br />{' '}
              <Text color={'blue.500'} as={'span'} fontSize={"40px"} fontStyle={"italic"}>
              Frame your social space
              </Text>{' '}
            </Heading>
          </Stack>
          <Image src={svg} />
        </VStack>
      </Flex>
      <Flex justifyContent={'center'} alignItems={'center'} width={'100%'}>
        <Container>
          <VStack as={'form'} p={10} borderRadius={'xl'} boxShadow={'2xl'} spacing={5}>
            <Heading>Login</Heading>
            <FormControl>
              <FormLabel>Email address</FormLabel>
              <Input
                onChange={ (e) =>handleInputChanges.emailChange (e.target.value)}
                value={loginFields.email}
                required
                name='email'
                type='email' />
              <span className="error">{loginFieldsErr.emailErr}</span>

            </FormControl>
            <FormControl>
              <FormLabel>Password</FormLabel>
              <Input
                onChange={(e) => handleInputChanges.passwordChange(e.target.value)}
                value={loginFields.password}
                name='password'
                type='password' />
              <span className="error">{loginFieldsErr.passwordErr}</span>

            </FormControl>
            <Flex justifyContent={"space-between"} width={"100%"}>
            <Text>New user?
               <Link to={"/"} className={"link"}>Register here</Link>
            </Text>
            
            <Button onClick={handleSubmit} colorScheme={'blue'} 
            isDisabled={Object.keys(loginFields).some(key => loginFields[key] === "") || isErr}
            >Submit</Button>
            </Flex>
            
          </VStack>
        </Container>
      </Flex>
    </Stack>
  )
}

export default Login