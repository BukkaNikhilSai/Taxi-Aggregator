package iiitb.ess201a7.r16091;

import iiitb.ess201a7.a7base.*;
import java.lang.*;
class Car16091 extends Car {

	private Location pre_loc;
	private Location sta_loc;
	private Location des_loc;
	public double sin,cos,tan;
	public Car16091(int id,int speed) {
		super(id,speed);
	}


	private int status_car=1;

	public int distSqrd(Location loc) {
		int x1=loc.getX(),y1=loc.getY();
		int x2=pre_loc.getX();
		int	y2=pre_loc.getY();
		int	dist=(int)Math.pow(x1-x2,2)+(int)Math.pow(y1-y2,2);
	return dist;
}
	@Override
	public void setLocation(Location l){
		// TODO Auto-generated method stub
		pre_loc=l;
	}

	@Override
	public Location getLocation(){
		// TODO Auto-generated method stub
		return pre_loc;
	}

	@Override
	public void setStatus(int s){
		// TODO Auto-generated method stub
		status_car=s;
	}

	@Override
	public int getStatus(){
		// TODO Auto-generated method stub
		return status_car;
	}

	@Override
	public void assignTrip(Trip trip) {
		// TODO Auto-generated method stub
		sta_loc=trip.getStart();
		des_loc=trip.getDest();
		status_car=2;
	}

	@Override
	public Location getStart() {
		// TODO Auto-generated method stub
		if(status_car!=1)
		{
			return sta_loc;
		}
		return null;
	}

	@Override
	public Location getDest() {
		// TODO Auto-generated method stub
		if(status_car!=1)
		{
			return des_loc;
		}
		return null;
	}
	public Trip getTrip(){
		Trip tri=new Trip(sta_loc,des_loc);
		return tri;
	}

	@Override
	public void updateLocation(double deltaT) {
	int x = pre_loc.getX() ;
	int y = pre_loc.getY() ;
	if(status_car == 2){
		double p = Math.sqrt(distSqrd(sta_loc)) ;
		if(maxSpeed * deltaT >= p){
			setStatus(3) ;
			x = sta_loc.getX() ;
			y = sta_loc.getY() ;
		}
		else {
			x += ((sta_loc.getX() - x) * maxSpeed * deltaT)/p ;
			y += ((sta_loc.getY() - y) * maxSpeed * deltaT)/p ;
		}
	}
	else if(status_car == 3){
		double p = Math.sqrt(distSqrd(des_loc)) ;
		if(maxSpeed * deltaT >= p){
			setStatus(1) ;
			x = des_loc.getX() ;
			y = des_loc.getY() ;
		}
		else {
			x += ((des_loc.getX() - x) * maxSpeed * deltaT)/p ;
			y += ((des_loc.getY() - y) * maxSpeed * deltaT)/p ;
		}
	}
	pre_loc.set(x,y) ;
}
}
