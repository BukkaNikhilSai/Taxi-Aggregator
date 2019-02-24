package iiitb.ess201a7.r16091;

import iiitb.ess201a7.a7base.*;
import java.util.*;

public class Fleet16091 extends Fleet {
	private ArrayList<Car> listofcar=new ArrayList<Car>();
	private Car temp;
	public int id=160910;
	public Fleet16091(String colour) {
		super(16091,colour);
}

	@Override
	public void addCar(int speed) {
		Car c=new Car16091(id+1,speed);
		id=id+1;
		listofcar.add(c);
	}

	@Override
	public Car findNearestCar(Location loc) {
		int car_i=0,count=0,count1=0;
		double time=0,min_time=0;

		for(int i=0;i<listofcar.size();i++)
		{
			temp=listofcar.get(i);
			time=temp.distSqrd(loc)/temp.getSpeed();
			if(temp.getStatus()==1){
					count1=1;
					if(count==0){min_time=time;count=1;car_i=i;}
					else if(count!=0 && min_time>=time){
					min_time=time;
					car_i=i;
					}
				}
			}
			if(count1==1)return listofcar.get(car_i);
			else {return null;}
	}

	public ArrayList<Car> getCars()
	{
		return listofcar;
	}
}
