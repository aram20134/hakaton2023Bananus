import { Avatar, Box, Card, CardContent, Grid, Typography } from '@mui/material';
import ArrowDownwardIcon from '@mui/icons-material/ArrowDownward';
import MoneyIcon from '@mui/icons-material/Money';
import ArrowUpward from '@mui/icons-material/ArrowUpward';
import WhatshotIcon from '@mui/icons-material/Whatshot';
import AcUnitIcon from '@mui/icons-material/AcUnit';
import ElectricBoltIcon from '@mui/icons-material/ElectricBolt';
export const Water = ({type, value}) => {
  switch (type) {
    case 'hot':
      return (
        <Card elevation={6} sx={{ height: '100%' }}>
          <CardContent>
            <Grid container spacing={3} sx={{ justifyContent: 'space-between' }}>
              <Grid item>
                <Typography color="textSecondary" gutterBottom variant="overline">
                  Расход горячей воды
                </Typography>
                <Typography color="textPrimary" variant="h4">
                  {value} M<span style={{fontSize:'16px', verticalAlign:'top'}}>3</span>
                </Typography>
              </Grid>
              <Grid item>
                <Avatar sx={{backgroundColor: 'error.main',height: 56,width: 56}}>
                  <WhatshotIcon />
                </Avatar>
              </Grid>
            </Grid>
            <Box sx={{pt: 2,display: 'flex',alignItems: 'center'}}>
              <ArrowDownwardIcon color="error" />
              <Typography color="error" sx={{mr: 1}}variant="body2">
                6%
              </Typography>
              <Typography color='error' variant="caption">
                за последний месяц
              </Typography>
            </Box>
          </CardContent>
        </Card>
      )
    case 'cold':
      return (
        <Card elevation={6} sx={{ height: '100%' }}>
          <CardContent>
            <Grid container spacing={3} sx={{ justifyContent: 'space-between' }}>
              <Grid item>
                <Typography color="textSecondary" gutterBottom variant="overline">
                  Расход холодной воды
                </Typography>
                <Typography color="textPrimary" variant="h4">
                  {value} M<span style={{fontSize:'16px', verticalAlign:'top'}}>3</span>
                </Typography>
              </Grid>
              <Grid item>
                <Avatar sx={{backgroundColor: 'success.main',height: 56,width: 56}}>
                  <AcUnitIcon />
                </Avatar>
              </Grid>
            </Grid>
            <Box sx={{pt: 2,display: 'flex',alignItems: 'center'}}>
              <ArrowUpward color="success" />
              <Typography color="success.main" sx={{mr: 1}}variant="body2">
                17%
              </Typography>
              <Typography color="success.main" variant="caption">
                за последний месяц
              </Typography>
            </Box>
          </CardContent>
        </Card>
      )
    case 'gas':
      return (
        <Card elevation={6} sx={{ height: '100%' }}>
          <CardContent>
            <Grid container spacing={3} sx={{ justifyContent: 'space-between' }}>
              <Grid item>
                <Typography color="textSecondary" gutterBottom variant="overline">
                  Расход электричества
                </Typography>
                <Typography color="textPrimary" variant="h4">
                  {value} кВт⋅ч
                </Typography>
              </Grid>
              <Grid item>
                <Avatar sx={{backgroundColor: 'primary.main',height: 56,width: 56}}>
                  <ElectricBoltIcon />
                </Avatar>
              </Grid>
            </Grid>
            <Box sx={{pt: 2,display: 'flex',alignItems: 'center'}}>
              <ArrowUpward color="success" />
              <Typography color="success.main" sx={{mr: 1}}variant="body2">
                2%
              </Typography>
              <Typography color="success.main" variant="caption">
                за последний месяц
              </Typography>
            </Box>
          </CardContent>
        </Card>
      )
    default:
      break;
  }
};
