import Head from 'next/head';
import NextLink from 'next/link';
import Router from 'next/router';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import {
  Box,
  Button,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  Checkbox,
  Container,
  FormHelperText,
  Grid,
  Link,
  TextField,
  Typography
} from '@mui/material';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import { DashboardLayout } from '../components/dashboard-layout';
import { CustomerListToolbar } from '../components/customer/layout-list';
import { useState } from 'react';
import { ProductCard } from '../components/product/product-card';
import ArrowRight from '@mui/icons-material/ArrowRight';

const mockSkud = [
  {houseName: 'houseName', address:'address',  skrud: [
    {name: 'Camera1', description: 'Вход в здание', type: 'Открыть дверь', lastEntered:'17.02.2023 15:20:09', camera: '/static/videos/camera1.mp4', timeLight: '14:00'},
    {name: 'Camera2', description: 'Вход в кабинет', type: 'Открыть дверь', lastEntered:'16.02.2023 16:35:59', camera: '/static/videos/camera2.mp4', timeLight: '20:00'},
    {name: 'Camera3', description: 'Ворота', type: 'Открыть ворота', lastEntered:'17.02.2023 17:10:12', camera: '/static/videos/vorota.mp4', timeLight: 'Никогда'},
  ]},
  {houseName: 'houseName', address:'address', skrud: [
    {name: 'Camera1', description: 'Вход в здание', type: 'Открыть дверь', lastEntered:'19.02.2023 15:01:55', camera: '/static/videos/camera1.mp4', timeLight: '16:00'},
    {name: 'Camera2', description: 'Вход в кабинет', type: 'Открыть дверь', lastEntered:'17.02.2023 18:50:31', camera: '/static/videos/camera1.mp4', timeLight: 'Всегда'},
    {name: 'Camera3', description: 'Ворота', type: 'Поднять ворота', lastEntered:'15.02.2023 09:20:33', camera: '/static/videos/camera1.mp4', timeLight: '15:00'},
  ]}
  
]

const Skud = () => {
  const [search, setSearch] = useState('')
  return (
    <>
      <Head>
        <title>
          СКУД и освещение | Bananos House Manager
        </title>
      </Head>
      <Box component="main" sx={{flexGrow: 1, py: 8}}>
        <Container maxWidth={false}>
          <CustomerListToolbar placeholder='Поиск планировок' title={'СКУД и освещение'} value={search} setValue={setSearch} />
            <Box sx={{ pt: 3 }}>
              <Grid container spacing={3}>
                {mockSkud.map((product) => {
                  return (
                    <Grid lg={12} md={12} xs={12} item>
                      <Card elevation={6}>
                        <CardHeader subheader={product.address}  title={product.houseName} />
                        <CardContent>
                          <Grid container spacing={2}>
                            {product.skrud.map((skrud) => (
                              <Grid item key={product.id} lg={4} md={12} xs={12}>
                                <ProductCard product={skrud} />
                              </Grid>
                            ))}
                          </Grid>
                        </CardContent>
                        <CardActions>
                          <Box sx={{display: 'flex', p: 1, justifyContent:'flex-end', width:'100%'}}>
                            <Button color="primary" endIcon={<ArrowRight fontSize="small" />} size='large' variant="text">
                              Все
                            </Button>
                          </Box>
                        </CardActions>
                      </Card>
                    </Grid>
                  )
                })}
              </Grid>
            </Box>
        </Container>
      </Box>
    </>
  )
};

Skud.getLayout = (page) => (
  <DashboardLayout>
    {page}
  </DashboardLayout>
);

export default Skud;
