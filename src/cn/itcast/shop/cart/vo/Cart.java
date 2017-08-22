package cn.itcast.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable{
	//���Ｏ�� key  PID  Map ɾ����
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	
	//Cart ����CartItem������
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	//�ܼ�
	private double total;
	
	public double getTotal() {
		return total;
	}

	//����������ӵ����ﳵ
	public void addCart(CartItem cartItem){
		/*�жϹ��ﳵ���Ƿ���ڸù����� 
		������� �������� 
		������ ֱ�����*/
		int pid = cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			CartItem _cartItem =map.get(pid);//ԭ���Ĺ�����
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			//������ ֱ�����
			map.put(pid, cartItem);
		}
		total += cartItem.getSubtotal();
	}
	//�ӹ��ﳵ���Ƴ�������
	public void removeCart(int pid){
		//�������Ƴ�  �ܼƼ�ȥ�Ƴ���С��
		CartItem cartItem=map.remove(pid);
		total -= cartItem.getSubtotal();
	}
	//��չ��ﳵ
	public void clearCart(){
		//�����й�������� �ܼ�����Ϊ0
		map.clear();
		total=0;
	}
}
