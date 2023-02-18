import Head from 'next/head';
import { Box, Card, CardHeader, CardMedia, Container, Grid, Typography } from '@mui/material';
import { CustomerListResults } from '../components/customer/customer-list-results';
import { CustomerListToolbar } from '../components/customer/layout-list';
import { DashboardLayout } from '../components/dashboard-layout';
import { customers } from '../__mocks__/customers';
import plan from '../../public/static/images/plan.png'
import { useEffect, useState } from 'react';
import { pl } from 'date-fns/locale';
import styles from '../theme/styles.module.css'

const mockPlans = [
  {title:'Первый этаж', subheader:'текст', img: '/static/images/plan2.jpg'},
  {title:'Второй этаж', subheader:'ещё текстик', img: '/static/images/plan2.jpg'},
  {title:'Третий этаж', subheader:'ещё текстик', img: '/static/images/plan2.jpg'},
]

const Page = () => {
  const [search, setSearch] = useState('')

  return (
    <>
      <Head>
        <title>
          Планировка | Bananos House Manager
        </title>
      </Head>
      <Box component="main" sx={{flexGrow: 1, py: 8}}>
        <Container maxWidth={false}>
          <CustomerListToolbar placeholder='Поиск планировок' title={'Планировка'} value={search} setValue={setSearch} />
            {/* <CustomerListResults customers={customers} /> */}
              <Grid style={{marginTop:'1rem'}} container spacing={3}>
                {mockPlans.filter((pl) => pl.title.toLowerCase().includes(search.toLowerCase())).map((plan) => 
                <Grid item lg={3} sm={6} xl={6} xs={12}>
                  <Card className={styles.animate}>
                    <CardHeader subheader={plan.subheader}  title={plan.title} />
                    <CardMedia component={'img'}  image={plan.img} />
                  </Card>
                </Grid>
                )}
              </Grid>
            {/* <Box sx={{mt:3, gap:3, display:'flex', flexDirection:'column'}}>
              {mockPlans.filter((pl) => pl.title.toLowerCase().includes(search.toLowerCase())).map((plan) => 
                <Card className={styles.animate}>
                  <CardHeader subheader={plan.subheader}  title={plan.title} />
                  <CardMedia component={'img'}  image={plan.img} />
                </Card>
              )}
          </Box> */}
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
