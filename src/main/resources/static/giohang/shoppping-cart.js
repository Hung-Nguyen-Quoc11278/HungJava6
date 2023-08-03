const app = angular.module("shopping-cart",[]);
app.controller("ctrl",function($scope,$http){
	//alert("Angular js initialized")
	// Quản Lý Giỏ Hàng
	$scope.cart = {
		items:[],
		// Them vao gio hang
		add(id){
		var item = this.items.find(item => item.id == id) // kiểm tra trong giỏi hàng dựa vào id
		if(item){
			alert("\n THÊM GIỎ HÀNG THÀNH CÔNG");
			item.qty++;
			this.saveToLocalStorage();		
		}else{
			$http.get(`/rest/product/${id}`).then(resp =>{
				resp.data.qty = 1;
				this.items.push(resp.data);
				this.saveToLocalStorage(); 					
			})
		}
		},
		//Xoa san pham khoi gio hang
		remove(id){
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index,1);
			this.saveToLocalStorage();
		},
		//xoa sach mat hang trong gio 
		clear(){
			this.items =[];
			this.saveToLocalStorage();
		},
		//tinh thanh tien
		amt_of(item){},
		//tinh tong so luong cac mat hang trong gio
		get count(){
			return this.items
			.map(item => item.qty)
			.reduce((total,qty)=> total += qty,0);
		},
		//Tổng thành số tiền các mặt hàng trong giỏ
	    get amount(){
			return this.items
			.map(item => item.qty * item.price)
			.reduce((total,qty)=> total += qty,0)
			
		},
	    // Lưu giỏ hàng vào local storage
		saveToLocalStorage(){
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart",json);
		},
		
		// doc gio hang tu local storage
			loadFromLocalStorage(){
				var json = localStorage.getItem("cart"); // doc cart tu local
				this.items = json ? JSON.parse(json) : []; // neu co chuyen sang json gan vao item ko thi rong
				
		},
		
	}
	
	
	
	$scope.cart.loadFromLocalStorage();
	
	$scope.order={
		createDate: new Date(),
		address:"",
		account:{username:$("#username").text()},
		get orderDetail(){
			return $scope.cart.items.map(item => {
				return{
					product:{id: item.id},
					price: item.price,
					quantity:item.qty
				}
			});
		},
		purchase(){
			var order = angular.copy(this);
			// thuc hien dat hang
			$http.post("/rest/orders",order).then(resp =>{
				alert("Đặt hàng thành Công");
				$scope.cart.clear();
				location.href = "/order/detail/" + resp.data.id;
			}).catch(error =>{
				alert("đặt hàng thất bại")
				console.log(error)
			})
		}
	}
	
	
})