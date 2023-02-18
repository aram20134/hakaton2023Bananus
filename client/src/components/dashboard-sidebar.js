import { useContext, useEffect, useState } from 'react';
import NextLink from 'next/link';
import { useRouter } from 'next/router';
import PropTypes from 'prop-types';
import { Box, Button, Divider, Drawer, FormControl, FormHelperText, InputLabel, MenuItem, Select, Typography, useMediaQuery } from '@mui/material';
import OpenInNewIcon from '@mui/icons-material/OpenInNew';
import { ChartBar as ChartBarIcon } from '../icons/chart-bar';
import { Cog as CogIcon } from '../icons/cog';
import { Lock as LockIcon } from '../icons/lock';
import { Selector as SelectorIcon } from '../icons/selector';
import { ShoppingBag as ShoppingBagIcon } from '../icons/shopping-bag';
import { User as UserIcon } from '../icons/user';
import { UserAdd as UserAddIcon } from '../icons/user-add';
import { Users as UsersIcon } from '../icons/users';
import { XCircle as XCircleIcon } from '../icons/x-circle';
import { Logo } from './logo';
import { NavItem } from './nav-item';
import { BananaLogo } from '../icons/bananaLogo';
import axios from 'axios'
import DashboardIcon from '@mui/icons-material/Dashboard';
import VideocamIcon from '@mui/icons-material/Videocam';
import BarChartIcon from '@mui/icons-material/BarChart';
import CurrencyRubleIcon from '@mui/icons-material/CurrencyRuble';
import { Context } from '../pages/_app';

const items = [
  {
    href: '/',
    icon: (<ChartBarIcon fontSize="small" />),
    title: 'Главная '
  },
  {
    href: '/layout',
    icon: (<DashboardIcon fontSize='small' />),
    title: 'Планировка'
  },
  {
    href: '/rent',
    icon: (<CurrencyRubleIcon fontSize="small" />),
    title: 'Аренда помещений'
  },
  {
    href: '/handling',
    icon: (<UserIcon fontSize="small" />),
    title: 'Обработка заявлений'
  },
  {
    href: '/CCTV',
    icon: (<VideocamIcon fontSize="small" />),
    title: 'Видеонаблюдение'
  },
  {
    href: '/meters',
    icon: (<BarChartIcon fontSize="small" />),
    title: 'Показание счётчиков'
  },
  {
    href: '/register',
    icon: (<UserAddIcon fontSize="small" />),
    title: 'СКУД и освещение'
  },
  // {
  //   href: '/404',
  //   icon: (<XCircleIcon fontSize="small" />),
  //   title: 'Error'
  // }
];


export const DashboardSidebar = (props) => {
  const { open, onClose } = props;
  const router = useRouter();
  const lgUp = useMediaQuery((theme) => theme.breakpoints.up('lg'), {
    defaultMatches: true,
    noSsr: false
  });
  const { setChosedHouse, chosedHouse, houses, setHouses } = useContext(Context)

  useEffect(() => {
    axios.get('http://10.2.0.84:9091/houses').then(({data}) => {setHouses(data), setChosedHouse(data[0])})
  }, [])
  

  useEffect(
    () => {
      if (!router.isReady) {
        return;
      }

      if (open) {
        onClose?.();
      }
    },
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [router.asPath]
  );

  const content = (
    <>
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          height: '100%'
        }}
      >
        <div>
          <Box sx={{ p: 3 }}>
            <NextLink href="/" passHref>
              <a style={{ display:'flex', flexDirection:'row', alignItems:'center', gap:'25px', textDecoration:'none', color:'white'}}>
                <Logo sx={{height: 42, width: 42}} />
                {/* <img src={'../../icons/BananaLogo.svg'} /> */}
                <Box sx={{textAlign:'center', fontSize:'20px'}}>
                  BHM
                </Box>
              </a>
            </NextLink>
          </Box>
          <Box sx={{textAlign:'center'}}>
              {/* {houses?.map((h) =><Option value={h.houseName}>{h.houseName}</Option>)} */}
            <FormControl  sx={{m:1, minWidth:'90%'}}>
              <InputLabel id='label'>Объект</InputLabel>
              <Select style={{color:'white'}} labelId="label" label='Объект' value={chosedHouse} onChange={(e) => setChosedHouse(e.target.value)}>
                {houses?.map((h, i) =><MenuItem value={h}>{h.houseName}</MenuItem>)}
              </Select>
              <FormHelperText>{chosedHouse && 'Улица: ' + chosedHouse.address}</FormHelperText>
            </FormControl>
          </Box>
        </div>
        <Divider
          sx={{
            borderColor: '#2D3748',
            my: 3
          }}
        />
        <Box sx={{ flexGrow: 1 }}>
          {items.map((item) => (
            <NavItem
              key={item.title}
              icon={item.icon}
              href={item.href}
              title={item.title}
            />
          ))}
        </Box>
        <Divider sx={{ borderColor: '#2D3748' }} />
      </Box>
    </>
  );

  if (lgUp) {
    return (
      <Drawer
        anchor="left"
        open
        PaperProps={{
          sx: {
            backgroundColor: 'neutral.900',
            color: '#FFFFFF',
            width: 280
          }
        }}
        variant="permanent"
      >
        {content}
      </Drawer>
    );
  }

  return (
    <Drawer
      anchor="left"
      onClose={onClose}
      open={open}
      PaperProps={{
        sx: {
          backgroundColor: 'neutral.900',
          color: '#FFFFFF',
          width: 280
        }
      }}
      sx={{ zIndex: (theme) => theme.zIndex.appBar + 100 }}
      variant="temporary"
    >
      {content}
    </Drawer>
  );
};

DashboardSidebar.propTypes = {
  onClose: PropTypes.func,
  open: PropTypes.bool
};
