import Head from 'next/head';
import { Box, Card, CardContent, CardHeader, CardMedia, Chip, Container, Grid, Pagination, Typography } from '@mui/material';
import { ProductListToolbar } from '../components/product/product-list-toolbar';
import { ProductCard } from '../components/product/product-card';
import { DashboardLayout } from '../components/dashboard-layout';
import { CustomerListToolbar } from '../components/customer/layout-list';
import { useState } from 'react';
import DoneIcon from '@mui/icons-material/Done';
import DoNotDisturbIcon from '@mui/icons-material/DoNotDisturb';
import SquareFootIcon from '@mui/icons-material/SquareFoot';
import CurrencyRubleIcon from '@mui/icons-material/CurrencyRuble';
import PersonIcon from '@mui/icons-material/Person';
import styles from '../theme/styles.module.css'

const mockRents = [
  {rentName: 'Подвал', square: 52.2, description: 'подвальное помещение, 2 отдельных входа', rented: true, renter: 'Aram Vardanyan', price: '80.000', before: '23.05.2023', img: '/static/images/podval.jpg'},
  {rentName: 'Офис 1', square: 11, description: 'подвальное помещение, 2 отдельных входа', rented: false, renter: 'Aram Vardanyan', price: '25.500', before: '23.05.2023', img: '/static/images/office2.jpeg'},
  {rentName: 'Офис 2', square: 31.3, description: 'подвальное помещение, 2 отдельных входа', rented: true, renter: 'Yakov Isakov', price: '30.000', before: '10.11.2023', img: '/static/images/office.jpg'},
  {rentName: 'Офис 3', square: 27.5, description: 'подвальное помещение, 2 отдельных входа', rented: false, renter: 'Yakov Isakov', price: '44.550', before: '10.11.2023', img: '/static/images/office3.jpg'}
]

const Page = () => {
  const [search, setSearch] = useState('')

  return (
    <>
      <Head>
        <title>
          Аренда помещений | Bananos House Manager
        </title>
      </Head>
      <Box component="main" sx={{flexGrow: 1, py: 8}}>
        <Container maxWidth={false}>
          <CustomerListToolbar value={search} setValue={setSearch} placeholder={'Поиск помещений'} title='Аренда помещений'  />
          <Box sx={{ pt: 3 }}>
            <Grid container spacing={3}>
              {mockRents.filter((rent) => rent.rentName.toLocaleLowerCase().includes(search.toLowerCase())).map((rent) => (
                <Grid className={styles.animate} item key={rent.rentName} lg={4} md={6} xs={12}>
                  <Card>
                    <CardHeader title={rent.rentName} subheader={rent.description} />
                    <CardMedia component={'img'}  image={rent.img} /> 
                    <CardContent>
                      <Grid container gap={2} flexDirection='column' lg={12} md={12} xs={12}>
                        <Chip icon={rent.rented ? <DoneIcon /> : <DoNotDisturbIcon />} color={rent.rented ? 'success' : 'error'} label={`Статус: ${rent.rented ? 'Свободно' : 'Арендовано до ' + rent.before}`} />
                        <Chip icon={<SquareFootIcon />} label={`Площадь: ${rent.square} кв.м`} />
                        <Chip icon={<CurrencyRubleIcon />} label={`Цена за месяц: ${rent.price} р.`} />
                        <Chip icon={<PersonIcon />} label={`Арендатор: ${!rent.rented ? rent.renter : 'Свободно'}`} />
                      </Grid>
                    </CardContent>
                  </Card>
                </Grid>
              ))}
            </Grid>
          </Box>
          <Box sx={{display: 'flex',justifyContent: 'center',pt: 3}}>
            <Pagination color="primary" count={3} size="small" />
          </Box>
        </Container>
      </Box>
    </>
  )
};

Page.getLayout = (page) => (
  <DashboardLayout>
    {page}
  </DashboardLayout>
);

export default Page;
