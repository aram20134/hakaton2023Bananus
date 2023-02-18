import { Avatar, Card, CardContent, Grid, Typography } from '@mui/material';
import HouseIcon from '@mui/icons-material/House';
import { Box } from '@mui/system';

export const TotalProfit = ({value}) => (
  <Card>
    <CardContent>
      <Grid container spacing={3} sx={{ justifyContent: 'space-between' }}>
        <Grid item>
          <Typography color="textSecondary" gutterBottom variant="overline">
            Количетво объектов
          </Typography>
          <Typography color="textPrimary" variant="h4">
            {value}
          </Typography>
        </Grid>
        <Grid item>
          <Avatar sx={{backgroundColor: 'secondary.main',height: 56,width: 56}}>
            <HouseIcon />
          </Avatar>
        </Grid>
      </Grid>
      <Box oxsx={{pt: 2,display: 'flex',alignItems: 'center'}}>
        {/* <ArrowUpward color="success" /> */}
        <Typography color="success.main" sx={{mr: 1}}variant="body2">
          ⠀
        </Typography>
        <Typography color="success.main" variant="caption">
          ⠀
        </Typography>
      </Box>
    </CardContent>
  </Card>
);
